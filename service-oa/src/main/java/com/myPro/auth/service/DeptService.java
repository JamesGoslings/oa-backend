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

    /**
     * 通过传入的部门id拿到该id和其所有子部门id构成的list
     * @param deptId 根部门id
     * */
    List<Long> getDeptIdListSelfAndChildren(Long deptId);

    /**
     * 获取全部的树型的部门信息
     * @return 树型部门列表
     */
    List<Dept> getAllTreeDeptList();

    /**
     * 获取全部的部门信息
     * @return 部门列表
     */
    List<Dept> getAllTotalDeptList();

    /**
     * 动态获取新的部门编码
     * @param deptId 待操作的部门的id，如果id为小于等于0或null就按新增部门来生成编码
     * @param parentId 该部门的上级部门的id
     * @return 生成的新的部门编码
     */
    String getNewCode(Long deptId, Long parentId);

    /**
     * 获取当前部门的总人数
     * @param deptId 当前部门的id
     * @return 部门的总人数
     */
    Long getDeptNum(Long deptId);
}
