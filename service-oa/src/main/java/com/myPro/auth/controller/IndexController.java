package com.myPro.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myPro.auth.service.SysMenuService;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.exception.MyException;
import com.myPro.common.jwt.JwtHelper;
import com.myPro.common.result.Result;
import com.myPro.common.utils.MD5;
import com.myPro.model.system.SysUser;
import com.myPro.vo.system.LoginVo;
import com.myPro.vo.system.RouterVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){

        String password = MD5.encrypt(loginVo.getPassword());
        String username = loginVo.getUsername();

        SysUser user = sysUserService.getOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        System.out.println("66662");
        if(user == null){
            throw new MyException(404,"用户不存在");
        }
        if(!Objects.equals(password, user.getPassword())){
            throw new MyException(201,"密码错误");
        }
        if(user.getStatus() == 0){
            throw new MyException(403,"该用户已被禁用");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",JwtHelper.createToken(user.getId(), user.getUsername()));
        return Result.ok(map);
    }

    /**
     * 获取用户信息（PC）
     * @return
     */
    @GetMapping("info")
    public Result info(HttpServletRequest request) {
        //TODO 从请求头获取用户信息(获取请求头token字符串)
        String token = request.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        String username = JwtHelper.getUsername(token);
        //TODO 获取用户姓名
        String name = sysUserService.getById(userId).getName();
        //TODO 获取用户可以操作的菜单
        List<RouterVo> routerList = sysMenuService.getUserMenuListByUser(userId);
        //TODO 获取用户可以操作的按钮列表
        List<String> permsList = sysMenuService.getUserPermsByUserId(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name",name);
        map.put("userId", userId);
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        //TODO 返回用户可以操作的菜单
        map.put("routers",routerList);
        //TODO 返回用户可以操作的按钮
        map.put("buttons",permsList);

        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}
