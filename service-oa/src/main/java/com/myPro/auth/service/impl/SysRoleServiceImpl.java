package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.SysRoleMapper;
import com.myPro.auth.mapper.SysUserRoleMapper;
import com.myPro.auth.service.SysRoleService;
import com.myPro.model.system.SysRole;
import com.myPro.model.system.SysUserRole;
import com.myPro.vo.system.AssignRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Map<String, Object> getRoleDataByUserId(Long userId) {
//        //查询所有的角色
//        List<SysRole> allRolesList = this.list();
//
//        //拥有的角色id
//        List<SysUserRole> existUserRoleList = sysUserRoleMapper.selectList(
//                new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId).
//                        select(SysUserRole::getRoleId));
//        List<Long> existRoleIdList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());
//
//        //对角色进行分类
//        List<SysRole> assginRoleList = new ArrayList<>();
//        for (SysRole role : allRolesList) {
//            //已分配
//            if(existRoleIdList.contains(role.getId())) {
//                assginRoleList.add(role);
//            }
//        }
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assginRoleList", getRolesByUserId(userId));
        roleMap.put("allRolesList", list());
        return roleMap;
    }

    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        //查询所有的角色
        List<SysRole> allRolesList = this.list();

        //拥有的角色id
        List<SysUserRole> existUserRoleList = sysUserRoleMapper.selectList(
                new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId).
                        select(SysUserRole::getRoleId));
        List<Long> existRoleIdList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());

        //对角色进行分类
        List<SysRole> assginRoleList = new ArrayList<>();
        for (SysRole role : allRolesList) {
            //已分配
            if(existRoleIdList.contains(role.getId())) {
                assginRoleList.add(role);
            }
        }
        return assginRoleList;
    }

    @Transactional
    @Override
    public void doAssign(AssignRoleVo assginRoleVo) {
        sysUserRoleMapper.delete(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, assginRoleVo.getUserId()));

        for(Long roleId : assginRoleVo.getRoleIdList()) {
            if(StringUtils.isEmpty(roleId)) continue;
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }
    }

}
