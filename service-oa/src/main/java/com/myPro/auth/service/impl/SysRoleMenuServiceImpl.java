package com.myPro.auth.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.SysRoleMenuMapper;
import com.myPro.auth.service.SysRoleMenuService;

import com.myPro.model.system.SysRoleMenu;
import org.springframework.stereotype.Service;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
}
