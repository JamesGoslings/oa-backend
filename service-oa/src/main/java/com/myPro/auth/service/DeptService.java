package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.Dept;
import com.myPro.vo.app.LinkManVo;

import java.util.List;

public interface DeptService extends IService<Dept> {

    // 以规定树型结构返回公司通讯录
    List<LinkManVo> getCompanyLinkMenInfo();

    // 根据传入的部门列表生成非树型的公司通讯录
    public List<LinkManVo> getOriginLinkManList(List<Dept> originDeptList);

    // 用部门总列表生成非树型的公司通讯录
    public List<LinkManVo> getOriginLinkManList();

    // 根据文字查询，结合多表查询获取列表元素封装成listVo的列表
    List<LinkManVo> getOriginListManByKey(String key);
}
