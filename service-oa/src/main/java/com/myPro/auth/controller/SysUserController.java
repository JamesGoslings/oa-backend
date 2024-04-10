package com.myPro.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.jwt.JwtHelper;
import com.myPro.common.result.Result;
import com.myPro.common.utils.FileUtil;
import com.myPro.common.utils.MD5;
import com.myPro.model.system.SysUser;
import com.myPro.vo.system.SysUserQueryVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dkh
 * @since 2024-03-03
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService service;

    //根据用户id修改用户状态
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id,
                              @PathVariable Integer status){
        service.updateStatus(id,status);
        return Result.ok();
    }

    //用户条件分页查询
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        SysUserQueryVo sysUserQueryVo){
        Page<SysUser> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();

        String username = sysUserQueryVo.getKeyword();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();

        //判断条件值不为空
        if(!StringUtils.isEmpty(username)){
            wrapper.like(SysUser::getUsername,username);
        }
        //ge大于等于
        if(!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysUser::getCreateTime,createTimeBegin);
        }
        //le小于等于
        if(!StringUtils.isEmpty(createTimeEnd)){
            wrapper.le(SysUser::getCreateTime,createTimeEnd);
        }
        return Result.ok(service.page(pageParam, wrapper));
    }


    @PostMapping("save")
    public Result save(@RequestBody SysUser user){
        user.setPassword(MD5.encrypt(user.getPassword()));
        if(service.save(user)){
            return Result.ok();
        }
        return Result.fail();
    }

    //根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        return Result.ok(service.getById(id));
    }

    @PutMapping("update")
    public Result update(@RequestBody SysUser user){
        if(service.updateById(user)){
            return Result.ok();
        }
        return Result.fail();
    }

    //根据id删除
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        if(service.removeById(id)){
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("updateAvatar")
    public Result updateAvatar(HttpServletRequest request)  throws FileNotFoundException{
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("myFile");
        String token = request.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        String avatarBase64Str = service.updateAvatar(file,userId);
        return Result.ok(avatarBase64Str);
    }

    // 上传并保存用户的头像并将新的图片转成base64字符串传回去
    @PostMapping("uploadAvatar")
    public Result uploadAvatar (HttpServletRequest  request) throws FileNotFoundException, UnknownHostException {
        String classpath = FileUtil.classpath;

        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("myFile");

        String token = request.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        String username = JwtHelper.getUsername(token);

        String suffix = FileUtil.getFileSuffix(file);

        String fileName = userId + "-" + username + suffix;
        if(!FileUtil.uploadFile(file, classpath + "/user-avatars/" , fileName)){
            return Result.build(503, "文件保存失败");
        }
        SysUser user = service.getById(userId);
//        user.setHeadUrl(classpath + "/user-avatars/" + fileName);
        user.setHeadUrl("/user-avatars/" + fileName);
        service.updateById(user);

        return  Result.ok(service.getAvatarBase64StrById(userId));
    }

    @GetMapping("getAvatar/{id}")
    public Result getAvatar (@PathVariable Long id){
//        SysUser user = service.getById(id);
//        String imgPath = user.getHeadUrl();
//        String base64Str = FileUtil.convertImageToBase64Str(FileUtil.classpath + imgPath);
//        String suffix = FileUtil.getFileSuffix(imgPath).substring(1);
//        if(!suffix.equals("gif")){
//            suffix = "png";
//        }
//        String finalBase64Str =  "data:image/" + suffix + ";base64," + base64Str;
//        System.out.println("finalBase64Str=============>");
//        System.out.println(finalBase64Str);
        return  Result.ok(service.getAvatarBase64StrById(id));
    }


}

