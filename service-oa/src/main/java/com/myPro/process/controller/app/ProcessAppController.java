package com.myPro.process.controller.app;


import com.myPro.common.result.Result;
import com.myPro.model.process.Process;
import com.myPro.model.process.ProcessType;
import com.myPro.process.service.ProcessService;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/app/process")
@CrossOrigin
public class ProcessAppController {

    @Autowired
    private ProcessTypeService processTypeService;

    @Autowired
    private ProcessService processService;

    //查询所有的审批分类和每个分类的模板
    @GetMapping("getProcessType")
    public Result getProcessType(){
        List<ProcessType> list = processTypeService.getProcessType();
        return Result.ok(list);
    }

    /**
     * 发起审批
     * @param process 审批对象
     * @return
     */
    @PostMapping("add")
    public Result addProcess(@RequestBody Process process){
        // 启动该流程实例并存下实例id
        process.setProcessInstanceId(processService.startUpProcess(process));
        process.setStatus(1);
        processService.save(process);
        return Result.ok();
    }
}
