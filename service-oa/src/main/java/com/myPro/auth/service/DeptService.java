package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.Dept;
import com.myPro.vo.app.LinkManVo;

import java.util.List;

public interface DeptService extends IService<Dept> {

    // 以规定树型结构返回公司通讯录
    List<LinkManVo> getCompanyLinkMenInfo();
}
