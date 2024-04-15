package com.myPro.auth.controller;

import com.myPro.auth.service.PrivateLinkManService;
import com.myPro.common.result.Result;
import com.myPro.model.app.PrivateLinkMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PutMapping("update")
    public Result updatePrivateLinkMan(@RequestBody PrivateLinkMan linkMan){
        linkMan.setUpdateTime(new Date());
        linkManService.updateById(linkMan);
        return Result.ok();
    }
}
