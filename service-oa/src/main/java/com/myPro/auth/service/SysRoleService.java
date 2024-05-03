package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysRole;
import com.myPro.vo.system.AssignRoleVo;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends IService<SysRole>{
    /**
     *查询所有角色 和 当前用户所属角色
     * */
    Map<String, Object> getRoleDataByUserId(Long userId);

    /**
     * 为用户分配角色
     * */
    void doAssign(AssignRoleVo assginRoleVo);

    /**
     * 根据userId获取对应角色的列表
     * */
    List<SysRole> getRolesByUserId(Long userId);
}
