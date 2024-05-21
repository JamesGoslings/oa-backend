package com.myPro.auth.service.utils;

import com.myPro.VO.app.LinkManVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于实现通讯录树型结构等的工具类
 * */
public class LinkManListHelper {

    /**
     * 将纯列表型的数据封装成一般树型（boss，总经理以及经理下一级同为一级）
     * */
    public static List<LinkManVo> buildCommonTree(List<LinkManVo> originList){
        List<LinkManVo> linkManVoList = new ArrayList<>();
        for (LinkManVo linkManVo :originList) {
            long parentId = linkManVo.getParentId();
            if(parentId < 2){
                //总经理和boss直接为起始节点
                linkManVoList.add(linkManVo);
            } else if(parentId == 2){
                // 以上一级为总经理的的部门为起始节点
                linkManVoList.add(getChildrenAndItSelf(linkManVo, originList));
                floor = 0;
            }
        }
        return linkManVoList;
    }

    private static Integer floor = 0;

    public static LinkManVo getChildrenAndItSelf(LinkManVo linkManVo,List<LinkManVo> originList){
        linkManVo.setChildren(new ArrayList<LinkManVo>());
        long itSelfId = linkManVo.getDeptId();
        for (LinkManVo originVo :originList) {
            long originParentId = originVo.getParentId();
            // 如果是非总经理和boos级别的第一部门，就建立子节点
            if(originVo.getChildren() == null && originParentId >= 2){
                originVo.setChildren(new ArrayList<LinkManVo>());
            }
            if(itSelfId == originParentId && originParentId >= 2){
                Integer tempFloor = floor;
                originVo.setFloor(++floor);
                linkManVo.getChildren().add(getChildrenAndItSelf(originVo,originList));
                floor = tempFloor;
            }
        }

        return linkManVo;
    }
}
