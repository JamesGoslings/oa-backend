package com.myPro.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.SysUserRoleMapper;
import com.myPro.auth.service.SysUserRoleService;
import com.myPro.model.web.SysUserRole;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
}
