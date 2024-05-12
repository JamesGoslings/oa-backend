package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.DeptMapper;
import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.SysUserService;
import com.myPro.auth.service.utils.DeptHelper;
import com.myPro.auth.service.utils.LinkManListHelper;
import com.myPro.model.system.Dept;
import com.myPro.vo.app.LinkManVo;
import com.myPro.vo.app.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return getOriginLinkManList(deptListByKey);
    }

    @Override
    public List<Long> getDeptIdListSelfAndChildren(Long deptId) {
        //TODO 拿到所有部门数据，然后进行筛选
        List<Dept> orginDeptList = list();
        // 如果是最高级直接返回所有id构成的list
        if(deptId <= 0){
            return baseMapper.getAllIdList();
        }
        // 先将根id添加进入
        List<Long> idList = new ArrayList<>();
        idList.add(deptId);
        return getChildrenId(deptId, orginDeptList,idList);
    }

    /**
     * 递归拿到给的根id的所有子id
     * */
    private List<Long> getChildrenId(Long parentDeptId,List<Dept> orginDeptList,List<Long> resList){
        for (Dept dept :orginDeptList) {
            // 是目标部门的子部门就进行添加并递归
            if(dept.getParentId().longValue() == parentDeptId.longValue()){
                resList.add(dept.getId());
                getChildrenId(dept.getId(), orginDeptList, resList);
            }
        }
        return resList;
    }


    @Override
    public List<Dept> getAllTreeDeptList() {
        // 构建成树型列表
        return DeptHelper.buildTree(getAllTotalDeptList());
    }

    @Override
    public List<Dept> getAllTotalDeptList() {
        // 获取所有的部门，每个部门附有该部门直接的人数
        List<Dept> originDeptList = baseMapper.selectAllListHasMyCount();
        // 创建一个Map来存储已经计算过的部门的totalCount，避免重复计算
        Map<Long, Long> totalCountMap = new HashMap<>();
        // 获取每个dept的总人数
        for (Dept dept : originDeptList) {
            if(dept.getIsAddChildrenCount() == 0){
                dept.setTotalCount(dept.getMyCount());
            }else {
                dept.setTotalCount(getOneDeptTotalCount(dept, originDeptList, totalCountMap));
            }
            // 封装负责人到dept中
            dept.setLeader(userService.getUserWebVoById(dept.getLeaderId()));
            if(dept.getParentId() != 0){
                dept.setParentName(getById(dept.getParentId()).getName());
            }
        }
        return originDeptList;
    }

    private Long getOneDeptTotalCount(Dept dept,List<Dept> allList, Map<Long, Long> totalCountMap){
        // 计算过了，直接返回
        if(totalCountMap.containsKey(dept.getId())){
            return totalCountMap.get(dept.getId());
        }
        Long total = dept.getMyCount();
        for (Dept childDept : allList) {
            // 找到子部门，子部门再递归
            if(Objects.equals(childDept.getParentId(), dept.getId())){
                total += getOneDeptTotalCount(childDept,allList,totalCountMap);
            }
        }
        // 记录计算
        totalCountMap.put(dept.getId(), total);
        return total;
    }


    @Override
    public String getNewCode(Long deptId, Long parentId) {
        if(parentId == 0){
            return count(new LambdaQueryWrapper<Dept>().eq(Dept::getParentId, 0)) + 1 + "";
        }
        // 拿到父级的编码
        String parentCode = getById(parentId).getDeptCode();
        // 拿到自己的序号
        String num = "";
        // 新建的序号
        if(deptId == null || deptId <= 0){
            long count = count(new LambdaQueryWrapper<Dept>().eq(Dept::getParentId, parentId));
            count++;
            num = count < 10 ? "0" + count : count + "";
        }else {
            String oldCode = getById(deptId).getDeptCode();
            num = oldCode.substring(oldCode.length() - 2);
        }
        return parentCode + num;
    }
}
