package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.SysMenu;
import com.myPro.model.system.SysRole;
import com.myPro.vo.system.AssignMenuVo;
import com.myPro.vo.system.ParentMenuVo;
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


    /**
     * 根据关键字查询菜单名相近的菜单
     * @param keyword 关键字
     * @return 相关菜单的列表（带父级名）
     * **/
    List<SysMenu> getMenusByKeyword(String keyword);

    /**
     * 获取所有的非按钮菜单及目录，并以字符串记录了层级关系表示
     * @return 所有的parentVo
     * */
    List<ParentMenuVo> getAllParentMenuVo();
}
