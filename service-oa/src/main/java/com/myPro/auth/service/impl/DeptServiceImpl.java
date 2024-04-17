package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.DeptMapper;
import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.SysUserService;
import com.myPro.auth.service.utils.LinkManListHelper;
import com.myPro.model.system.Dept;
import com.myPro.vo.app.LinkManVo;
import com.myPro.vo.app.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private DeptMapper deptMapper;

    // 以规定树型结构返回公司通讯录
    @Override
    public List<LinkManVo> getCompanyLinkMenInfo() {
        // 原始数据
        List<Dept> originDeptList = list();
        // 将原始数据封装到linkManVoList中(非树型结构)
        List<LinkManVo> linkManVoList = getOriginLinkManList(originDeptList);

        // 封装成一般的树型机构后返回
        return LinkManListHelper.buildCommonTree(linkManVoList);
    }

    @Override
    public List<LinkManVo> getOriginLinkManList(List<Dept> originDeptList){
        // 待填入的列表
        List<LinkManVo> linkManVoList = new ArrayList<>();

        // 将原始数据封装到linkManVoList中(非树型结构)
        for (Dept dept :originDeptList) {
            LinkManVo linkManVo = new LinkManVo();
            linkManVo.setDeptName(dept.getName());
            SysUserVo userVo = userService.getUserVoById(dept.getLeaderId(),false);
            linkManVo.setLeader(userVo);
            linkManVo.setParentId(dept.getParentId());
            linkManVo.setDeptId(dept.getId());
            linkManVo.setTypeId(1L);
            linkManVo.setTypeName("公司通讯录");
            linkManVoList.add(linkManVo);
        }
        return linkManVoList;
    }

    @Override
    public List<LinkManVo> getOriginLinkManList() {
        return getOriginLinkManList(list());
    }

    @Override
    public List<LinkManVo> getOriginListManByKey(String key) {
        List<Dept> deptListByKey = deptMapper.getDeptListByKey(key);
        List<LinkManVo> originLinkManList = getOriginLinkManList(deptListByKey);
        System.out.println("====================NB=====================");
        originLinkManList.forEach(System.out::println);
        System.out.println("====================NB=====================");
        return originLinkManList;
    }
}
