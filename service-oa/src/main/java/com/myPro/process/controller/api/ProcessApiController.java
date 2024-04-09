package com.myPro.process.controller.api;


import com.myPro.common.result.Result;
import com.myPro.model.process.ProcessType;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/api/process")
@CrossOrigin
public class ProcessApiController {

    @Autowired
    private ProcessTypeService processTypeService;

    //查询所有的审批分类和每个分类的模板
    @GetMapping("getProcessType")
    public Result getProcessType(){
        List<ProcessType> list = processTypeService.getProcessType();
        return Result.ok(list);
    }
}
