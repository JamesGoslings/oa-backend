package com.myPro.auth.controller;

import com.myPro.auth.service.DeptService;
import com.myPro.common.result.Result;
import com.myPro.model.system.Dept;
import com.myPro.vo.app.LinkManVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 获取规定树型的公司通讯录
    @GetMapping("linkManListInfo")
    public Result getLinkMenInfo(){
        List<LinkManVo> linkManVoList = deptService.getCompanyLinkMenInfo();
        return Result.ok(linkManVoList);
    }

    // 直接获取列表型的所有原始部门数据
    @GetMapping("all")
    public Result getAllDeptList(){
        return Result.ok(deptService.list());
    }

    // 获取web端需要的树型结构的所有的部门信息
    @GetMapping("allTree")
    public Result getAllTreeDeptList(){
        List<Dept> treeDeptList = deptService.getAllTreeDeptList();
        return Result.ok(treeDeptList);
    }
}
