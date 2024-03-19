package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysUser;

public interface SysUserService extends IService<SysUser>{
    void updateStatus(Long id, Integer status);

    SysUser getUserByUsername(String username);

}
