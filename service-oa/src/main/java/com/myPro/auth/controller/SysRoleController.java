package com.myPro.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myPro.auth.service.SysRoleService;

import com.myPro.model.system.SysRole;
import com.myPro.vo.system.AssignRoleVo;
import com.myPro.vo.system.SysRoleQueryVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.myPro.common.result.Result;


import java.util.List;
import java.util.Map;


//localhost:8800/admin/system/sysRole/findAll
@Api("角色管理接口")
@Slf4j
@RestController
@RequestMapping("admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //获取角色
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable Long userId){
        Map<String,Object> map = sysRoleService.getRoleDataByUserId(userId);
        return Result.ok(map);
    }

    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignRoleVo assignRoleVo){
        sysRoleService.doAssign(assignRoleVo);
        return Result.ok();
    }

    @GetMapping("/getAll")
    public Result<List<SysRole>> getAll(){
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo){
        Page<SysRole> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(SysRole::getRoleName,roleName);
        }
        return Result.ok(sysRoleService.page(pageParam,wrapper));
    }

    @PostMapping("save")
    public Result save(@RequestBody SysRole role){
        if(sysRoleService.save(role)){
            return Result.ok();
        }
        return Result.fail();
    }

    //根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        return Result.ok(sysRoleService.getById(id));
    }

    @PutMapping("update")
    public Result update(@RequestBody SysRole role){
        if(sysRoleService.updateById(role)){
            return Result.ok();
        }
        return Result.fail();
    }

    //根据id删除
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        if(sysRoleService.removeById(id)){
            return Result.ok();
        }
        return Result.fail();
    }

    //批量删除
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        if(sysRoleService.removeByIds(idList)){
            return Result.ok();
        }
        return Result.fail();
    }

}
