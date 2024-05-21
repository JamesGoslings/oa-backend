package com.myPro.auth.service.utils;

import com.myPro.model.web.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    /**
     * 用递归建层级菜单
     * */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();

        //遍历菜单数据
        for (SysMenu sysMenu : sysMenuList) {
            if(sysMenu.getParentId() == 0){
                trees.add(getChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu,List<SysMenu> sysMenuList){
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu it : sysMenuList) {
            if(sysMenu.getChildren() == null){
                sysMenu.setChildren(new ArrayList<>());
            }
            if(sysMenu.getId().longValue() == it.getParentId().longValue()){
                sysMenu.getChildren().add(getChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }
}


