package com.myPro.auth.service.impl;

import com.myPro.auth.service.SysMenuService;
import com.myPro.auth.service.SysUserService;
import com.myPro.model.web.SysUser;
//import com.myPro.security.custom.UserDetailsService;
import com.myPro.security.custom.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserService.getUserByUsername(username);
        if(null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if(sysUser.getStatus() == 0) {
            throw new RuntimeException("账号已停用");
        }
        //根据userId查询用户能操作的菜单的列表
        List<String> userPermsList = sysMenuService.getUserPermsByUserId(sysUser.getId());
        //创建List集合，封装最终的权限数据
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        for (String perm : userPermsList) {
            authList.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, authList);
    }
}
