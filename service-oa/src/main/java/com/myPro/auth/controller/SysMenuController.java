package com.myPro.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myPro.auth.service.SysMenuService;
import com.myPro.common.result.Result;
import com.myPro.model.system.SysMenu;
import com.myPro.model.system.SysRole;
import com.myPro.vo.system.AssignMenuVo;
import com.myPro.vo.system.ParentMenuVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<SysMenu> keyMenuList = sysMenuService.getMenusByKeyword(keyword);
        return Result.ok(keyMenuList);
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
    public Result save(@RequestBody SysMenu permission) {
        sysMenuService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody SysMenu permission) {
        sysMenuService.updateById(permission);
        return Result.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysMenuService.removeById(id);
        return Result.ok();
    }
}
