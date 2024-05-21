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
        // TODO 拿到第一个任务的审批人
        process.setCurrentAuditor(processService.getCurrentAuditorByInstanceId(process.getProcessInstanceId()));
        // TODO 保存审批
        processService.save(process);
        // TODO 对当前审批添加一条记录
        processService.recordThisProcess(process.getId());
        return Result.ok();
    }

    /**
     * 查看当前用户待处理的他人发过来的审批
     * @param userId 当前用户的id
     * @return 待处理的审批
     */
    @GetMapping("doProcess/{userId}")
    public Result listDoingProcess(@PathVariable Long userId){
        List<ProcessVo> processVos = processService.listMyDoingProcess(userId);
        return Result.ok(processVos);
    }

    /**
     * 通过流程的id来推动当前流程
     * @param processId 流程id
     * @param userId 操作用户的id
     *@return
     */
    @PostMapping("doTask/{processId}/{userId}")
    public Result doTask(@PathVariable Long processId,
                         @PathVariable Long userId){
        if(!processService.doTaskByProcessId(processId,userId)){
            return Result.fail("你不是当前审批的人，不能推动该流程");
        }
        return Result.ok();
    }

    /**
     * 实现流程的驳回操作
     * @param processId 流程的id
     * @param userId 操作者id
     * @return
     */
    @DeleteMapping("quit/{processId}/{userId}")
    public Result quitProcess(@PathVariable Long processId,
                              @PathVariable Long userId){
        if(!processService.quitProcessInstance(processId,userId)){
            return Result.fail("一边去，你不是当前的审批者，没有驳回权限");
        }
        return Result.ok();
    }

    /**
     * 获取该用户自己发出申请
     * @param userId 用户的id
     * @param status 申请的状态
     * @return 申请的list
     */
    @GetMapping("myProcess/{userId}/{status}")
    public Result listMyProcess(@PathVariable Long userId,
                                @PathVariable Integer status){
        List<ProcessVo> processVos = processService.listMyProcess(userId,status);
        return Result.ok(processVos);
    }
}
