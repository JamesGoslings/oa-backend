package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysMenu;
import com.myPro.vo.system.AssignMenuVo;
import com.myPro.vo.system.RouterVo;

import java.util.List;

public interface SysMenuService extends IService<SysMenu>{
    List<SysMenu> getNodes();

    void removeMenuById(Long id);

    List<SysMenu> getMenuByRoleId(Long roleId);

    void doAssign(AssignMenuVo assignMenuVo);

    List<RouterVo> getUserMenuListByUser(Long userId);

    List<String> getUserPermsByUserId(Long userId);

}
