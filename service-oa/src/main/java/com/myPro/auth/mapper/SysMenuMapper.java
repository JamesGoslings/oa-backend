package com.myPro.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.system.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> getMenuListByUserId(@Param("userId") Long userId);

    /**
     * 通过roleId拿到所有可操作的菜单的parentId
     * */
    List<SysMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
}
