package com.myPro.process.controller.app;


import com.myPro.common.result.Result;
import com.myPro.model.process.Process;
import com.myPro.model.process.ProcessType;
import com.myPro.process.service.ProcessService;
import com.myPro.process.service.ProcessTypeService;
import com.myPro.vo.process.ProcessVo;
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
        // TODO 启动该流程实例并存下实例id
        process.setProcessInstanceId(processService.startUpProcess(process));
        process.setStatus(1);
        // 拿到第一个任务的审批人
        process.setCurrentAuditor(processService.getCurrentAuditorByInstanceId(process.getProcessInstanceId()));
        processService.save(process);
        return Result.ok();
    }

    /**
     * 查看当前用户有无待处理的他人发过来的审批
     * @param userId 当前用户的id
     * @return 待处理的审批
     */
    @GetMapping("doProcess/{userId}")
    public Result listDoingProcess(@PathVariable Long userId){
        List<ProcessVo> processVos = processService.listMyDoingProcess(userId);
        return Result.ok(processVos);
    }
}
