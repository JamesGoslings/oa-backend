package com.myPro.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.SysMenuMapper;
import com.myPro.auth.service.SysMenuService;
import com.myPro.auth.service.SysRoleMenuService;
import com.myPro.auth.service.utils.MenuHelper;
import com.myPro.common.exception.MyException;
import com.myPro.model.web.SysMenu;
import com.myPro.model.web.SysRoleMenu;
import com.myPro.VO.system.AssignMenuVo;
import com.myPro.VO.system.MetaVo;
import com.myPro.VO.system.ParentMenuVo;
import com.myPro.VO.system.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysMenu> getNodes() {
        //取出全部数据
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        //构建树形结构并返回
        return MenuHelper.buildTree(sysMenuList);
    }

    //删除菜单
    @Override
    public void removeMenuById(Long id) {
        //判断当前菜单是否有下一层菜单
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getParentId, id));
        if(count > 0){
            throw new MyException(201,"菜单不能删除");
        }
        baseMapper.deleteById(id);
    }


    //查询所有菜单和角色分配的菜单
    @Override
    public List<SysMenu> getMenuByRoleId(Long roleId) {
        //1、查所有可用菜单(status == 1)
        List<SysMenu> allSysMenuList = baseMapper.selectList(
                new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus,1));

        //2、根据roleId查询 角色菜单关系表里面 角色id对应所有的菜单id
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(
                new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId,roleId));
        List<Long> menuIdList = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : sysRoleMenuList) {
            menuIdList.add(sysRoleMenu.getMenuId());
        }

        //3、根据菜单id和所有菜单集合里面id进行比较，如果相同就封装
        allSysMenuList.forEach(item -> {
            item.setSelect(menuIdList.contains(item.getId()));
        });

        //4 返回树形显示格式菜单列表
        return MenuHelper.buildTree(allSysMenuList);
    }

    //给角色分配菜单
    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {
        //1、根据角色id删除 菜单角色表 分配数据
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId,assignMenuVo.getRoleId()));
        //2、从参数中拿到角色新分配的菜单id，再把每个菜单id都添加到菜单角色关系表中
        List<Long> menuIdList = assignMenuVo.getMenuIdList();
        for (Long menuId :menuIdList) {
            if(StringUtils.isEmpty(menuId)){
                continue;
            }
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
            sysRoleMenuService.save(sysRoleMenu);
        }
    }

    //通过userId返回用户可以操作的菜单
    @Override
    public List<RouterVo> getUserMenuListByUser(Long userId) {
        List<SysMenu> sysMenuList;
        //如果用户是admin,直接返回所有菜单
        if(userId == 1L){
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus,1);
            wrapper.orderByAsc(SysMenu::getSortValue);
            sysMenuList = baseMapper.selectList(wrapper);
        }else {
            //非管理员情况
            //多表查询,  用户角色菜单表->角色菜单关系表->菜单表
            //用userId获取对应的角色id集合,再通过角色id获取对应的menu集合
            sysMenuList = baseMapper.getMenuListByUserId(userId);
        }
        //TODO 转成树形结构
        //     1、转成无属性的树形
        //     2、添加属性转成路由的集合
        return this.buildRouterList(MenuHelper.buildTree(sysMenuList));
    }

    //将一般的树形结构构建成路由结构
    private List<RouterVo> buildRouterList(List<SysMenu> menus) {
        List<RouterVo> routers = new ArrayList<>();
        //遍历menus
        for (SysMenu menu :menus) {
            RouterVo router = new RouterVo();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            //下一层数据部分
            List<SysMenu> children = menu.getChildren();
            //非顶级菜单的菜单
            if(menu.getType() == 1){
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> !StringUtils.isEmpty(item.getComponent()))
                        .toList();
                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVo hiddenRouter = new RouterVo();
                    //隐藏式路由
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hiddenMenu.getName(), hiddenMenu.getIcon()));

                    routers.add(hiddenRouter);
                }
            }else {
                if(!CollectionUtils.isEmpty(children)){
                    if(children.size() > 0){
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRouterList(children));
                }
            }
            routers.add(router);
        }

        return routers;
    }

    /**
     * 获取路由地址
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if(menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }

    //根据userId返回用户能操作的的菜单、按钮列表
    @Override
    public List<String> getUserPermsByUserId(Long userId) {
        List<SysMenu> sysMenuList;
        //如果用户是admin,直接返回所有按钮列表
        if(userId == 1L){
            LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysMenu::getStatus,1);
            sysMenuList = baseMapper.selectList(wrapper);
        }else {
            //非管理员情况
            sysMenuList = baseMapper.getMenuListByUserId(userId);
        }
        //从查出来的数据中获取可操作的按钮list集合
        return sysMenuList.stream()
                .filter(item -> item.getType() == 2)
                .map(SysMenu::getPerms)
                .toList();
    }

    @Override
    public List<Long> getMyIdsWithoutChildren(Long roleId) {
        // 拿到该角色的所有菜单的list
        List<SysMenu> menuList = baseMapper.getMenuListByRoleId(roleId);
        // TODO 拿到menuList中没有子节点的menuId
        List<Long> idListWithoutChildren = new ArrayList<>();

        // 获取所有的id
        List<Long> allIdList = new ArrayList<>();
        // 装所有parentId
        HashSet<Long> parentIdSet = new HashSet<>();
        for (SysMenu sysMenu :menuList) {
            parentIdSet.add(sysMenu.getParentId());
            allIdList.add(sysMenu.getId());
        }
        for (Long id : allIdList) {
            if(!parentIdSet.contains(id)){
                idListWithoutChildren.add(id);
            }
        }
        return idListWithoutChildren;
    }

    @Override
    public List<ParentMenuVo> getAllParentMenuVo() {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getType, 0)
                .or()
                .eq(SysMenu::getType, 1);
        // 拿到所有非按钮的菜单
        List<SysMenu> menuList = list(wrapper);
        // 将id和自身name映射起来，存入Map
        Map<Long, SysMenu> map = new HashMap<>();
        for (SysMenu menu : menuList) {
            map.put(menu.getId(), menu);
        }
        List<ParentMenuVo> parentMenuVos = new ArrayList<>();
        for (SysMenu menu : menuList) {
            ParentMenuVo vo = new ParentMenuVo();
            vo.setId(menu.getId());
            vo.setName(menu.getName());
            if(menu.getParentId() == 0){
                vo.setTotalName(menu.getName());
            }else {
                vo.setTotalName(getOneTotalName(vo.getId(), map, menu.getParentId()));
            }
            parentMenuVos.add(vo);
        }
        return parentMenuVos;
    }

    // 通过全菜单的map为单个vo(非顶级菜单对应的vo)设置全名称(带所有父级关系的字符串)
    private String getOneTotalName(Long id,Map<Long, SysMenu> idMenuMap,Long parentId){
        Long endParentId = -1L;
        StringBuilder myTotalName = new StringBuilder(idMenuMap.get(id).getName());
        while (endParentId != 0L){
            SysMenu parentMenu = null;
            if(endParentId == -1L){
                parentMenu = idMenuMap.get(parentId);
            }else {
                parentMenu = idMenuMap.get(endParentId);
            }
            // 当前的直接父名称
            String upParentName = parentMenu.getName();
            endParentId = parentMenu.getParentId();
            myTotalName.insert(0, upParentName + ">>");
        }
        return myTotalName.toString();
    }
}
