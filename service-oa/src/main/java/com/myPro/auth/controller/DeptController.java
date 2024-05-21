package com.myPro.auth.controller;

import com.myPro.auth.service.DeptService;
import com.myPro.common.result.Result;
import com.myPro.model.web.Dept;
import com.myPro.VO.app.LinkManVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    // 获取所有部门的完整信息（列表结构）
    @GetMapping("allList")
    public Result getAllTotalDeptList(){
        List<Dept> deptList = deptService.getAllTotalDeptList();
        return Result.ok(deptList);
    }

    @PutMapping("update")
    public Result updateDept(@RequestBody Dept dept){
        deptService.updateById(dept);
        return Result.ok();
    }

    @PostMapping("save")
    public Result saveDept(@RequestBody Dept dept){
        deptService.save(dept);
        return Result.ok();
    }

    @GetMapping("newCode/{deptId}/{parentId}")
    public Result getNewDeptCode(@PathVariable Long deptId,
                                 @PathVariable Long parentId){
        String newCode = deptService.getNewCode(deptId,parentId);
        HashMap<String, String> map = new HashMap<>();
        map.put("newDeptCode", newCode);
        return Result.ok(map);
    }

    @DeleteMapping("remove/{deptId}")
    public Result removeOneDept(@PathVariable Long deptId){
        deptService.removeById(deptId);
        return Result.ok();
    }

}
