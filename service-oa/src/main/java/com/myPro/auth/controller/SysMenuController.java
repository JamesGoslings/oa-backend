package com.myPro.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myPro.auth.service.SysMenuService;
import com.myPro.common.result.Result;
import com.myPro.model.web.SysMenu;
import com.myPro.VO.system.AssignMenuVo;
import com.myPro.VO.system.ParentMenuVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        List<SysMenu> list = sysMenuService.getMenuByRoleId(roleId);
        return Result.ok(list);
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignMenuVo assignMenuVo) {
        sysMenuService.doAssign(assignMenuVo);
        return Result.ok();
    }

    @GetMapping("/keyMenu")
    public Result getMenuNodesByKeyword(String keyword){
        // TODO 拿到所有相关菜单（普通列表）
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
            wrapper.like(SysMenu::getName, keyword);
        }
        List<SysMenu> menuList = sysMenuService.list(wrapper);
        return Result.ok(menuList);
    }

    @GetMapping("getMyIdsWithoutChildren/{roleId}")
    public Result getIdListWithoutChildrenByRoleId(@PathVariable Long roleId){
        List<Long> idListWithoutChildren = sysMenuService.getMyIdsWithoutChildren(roleId);
        return Result.ok(idListWithoutChildren);
    }

    @GetMapping("getParentMenuAll")
    public Result getParentMenuAll(){
        List<ParentMenuVo> parentMenuVoList = sysMenuService.getAllParentMenuVo();
        return Result.ok(parentMenuVoList);
    }

    @GetMapping("getNodes")
    public Result getNodes() {
        List<SysMenu> list = sysMenuService.getNodes();
        return Result.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu menu) {
        // TODO 如果新建的菜单是非按钮菜单且path又是空，就将path赋随机值,防止动态生成路由时产生冲突
        // 检测是否是将原本的按钮菜单改成了非按钮菜单
        if((menu.getType() == 0 || menu.getType() == 1) && StringUtils.isEmpty(menu.getPath())) {
            menu.setPath(UUID.randomUUID().toString());
        }
        sysMenuService.save(menu);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu menu) {
        // TODO 如果从按钮改成了非按钮菜单且path又没改，就将path赋随机值,防止动态生成路由时产生冲突
        // 检测是否是将原本的按钮菜单改成了非按钮菜单
        if((menu.getType() == 0 || menu.getType() == 1) && StringUtils.isEmpty(menu.getPath())) {
            menu.setPath(UUID.randomUUID().toString());
        }
        sysMenuService.updateById(menu);
        return Result.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.ok();
    }
}
