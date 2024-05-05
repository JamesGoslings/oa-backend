package com.myPro.auth.controller;

import com.myPro.auth.service.SysMenuService;
import com.myPro.common.result.Result;
import com.myPro.model.system.SysMenu;
import com.myPro.model.system.SysRole;
import com.myPro.vo.system.AssignMenuVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("getMyIdsWithoutChildren/{roleId}")
    public Result getIdListWithoutChildrenByRoleId(@PathVariable Long roleId){
        List<Long> idListWithoutChildren = sysMenuService.getMyIdsWithoutChildren(roleId);
        return Result.ok(idListWithoutChildren);
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
