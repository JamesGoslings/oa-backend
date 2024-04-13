package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.DeptMapper;
import com.myPro.auth.mapper.SysUserMapper;
import com.myPro.auth.service.DeptService;
import com.myPro.model.system.Dept;
import com.myPro.model.system.SysUser;
import com.myPro.vo.app.LinkManVo;
import com.myPro.vo.app.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Autowired
    private SysUserMapper userMapper;

    // 以规定树型结构返回公司通讯录
    @Override
    public LinkManVo getCompanyLinkMenInfo() {
        // 原始数据
        List<Dept> originDeptList = list();
        // 待填入的数据结构
        List<LinkManVo> linkManVoList = new ArrayList<>();
        for (Dept dept :originDeptList) {
            LinkManVo linkManVo = new LinkManVo();
            linkManVo.setDeptName(dept.getName());
            SysUser user = userMapper.selectById(dept.getLeaderId());
            SysUserVo userVo = new SysUserVo();
            userVo.setDept(dept.getName());
            userVo.setName(user.getName());
//            userVo.setPost();
        }

        return null;
    }
}
