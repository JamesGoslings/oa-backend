package com.myPro.auth.controller;

import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.PrivateLinkManService;
import com.myPro.auth.service.PublicLinkManService;
import com.myPro.common.result.Result;
import com.myPro.model.app.PrivateLinkMan;
import com.myPro.VO.app.LinkManVo;
import com.myPro.VO.app.TotalLinkManVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/system/linkMan")
public class LinkManController {

    @Autowired
    private PrivateLinkManService privateLinkManService;

    @Autowired
    private PublicLinkManService publicLinkManService;

    @Autowired
    private DeptService deptService;

    @GetMapping("getPrivateList/{userId}")
    public Result getPrivateLinkManList(@PathVariable Long userId){
        List<PrivateLinkMan> linkManList = privateLinkManService.getLinkManListByUserId(userId);
        return Result.ok(linkManList);
    }

    @GetMapping("getPublicList")
    public Result getPublicLinkManList(){
        return Result.ok(publicLinkManService.list());
    }

    @PutMapping("updatePrivateLinkMan")
    public Result updatePrivateLinkMan(@RequestBody PrivateLinkMan linkMan){
        linkMan.setUpdateTime(new Date());
        privateLinkManService.updateById(linkMan);
        return Result.ok();
    }

    @GetMapping("searchLinkManList/{userId}/{key}")
    public Result getLinkManList(@PathVariable Long userId,@PathVariable String key){

        List<LinkManVo> companyLinkManList = deptService.getOriginListManByKey(key);

        List<TotalLinkManVo> privateLinkManList = privateLinkManService.getLinkManListByUserIdAndKey(userId,key);

        List<TotalLinkManVo> publicLinkManList = publicLinkManService.getLinkManListByKey(key);

        ArrayList<Object> resultList = new ArrayList<>();
        resultList.addAll(companyLinkManList);
        resultList.addAll(privateLinkManList);
        resultList.addAll(publicLinkManList);

        return Result.ok(resultList);
    }

    @PostMapping("savePrivateLinkMan")
    public Result savePrivateLinkMan(@RequestBody PrivateLinkMan linkMan){
        privateLinkManService.save(linkMan);
        return Result.ok();
    }

    @DeleteMapping("remove/{id}")
    public Result removePrivateLinkMan(@PathVariable Long id){

        privateLinkManService.removeById(id);
        return Result.ok();
    }

}

