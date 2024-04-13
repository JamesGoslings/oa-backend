package com.myPro.auth.controller;

import com.myPro.auth.service.DeptService;
import com.myPro.common.result.Result;
import com.myPro.vo.app.LinkManVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/system/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 获取规定树型的公司通讯录
    @GetMapping("linkMenInfo")
    public Result getLinkMenInfo(){
        LinkManVo linkManVo = deptService.getCompanyLinkMenInfo();
        return Result.ok(linkManVo);
    }
}