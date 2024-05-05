package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysMenu;
import com.myPro.model.system.SysRole;
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

    /**
     * 根据roleId拿到该角色下所有拥有菜单中，没有子菜单的菜单id
     * @param roleId 对应的角色id
     * @return 没有子节点的菜单id列表
     * */
    List<Long> getMyIdsWithoutChildren(Long roleId);
}
