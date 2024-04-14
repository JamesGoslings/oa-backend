package com.myPro.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myPro.auth.service.PrivateLinkManService;
import com.myPro.common.result.Result;
import com.myPro.model.app.PrivateLinkMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/system/privateLinkMan")
public class PrivateLinkManController {

    @Autowired
    private PrivateLinkManService linkManService;

    @GetMapping("getList/{userId}")
    public Result getPrivateLinkManList(@PathVariable Long userId){
        List<PrivateLinkMan> linkManList = linkManService.getLinkManListByUserId(userId);
        return Result.ok(linkManList);
    }
}
