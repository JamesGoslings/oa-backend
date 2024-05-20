package com.myPro.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.process.Process;
import com.myPro.vo.process.ProcessQueryVo;
import com.myPro.vo.process.ProcessVo;

import java.util.List;

public interface ProcessService extends IService<Process> {

    /**
     * 获取审批列表
     * @param pageParam 查询的分页信息
     * @param processQueryVo 查询的条件
     * @return  查询封装到vo中的结果
     */
    IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, ProcessQueryVo processQueryVo);

    /**
     * 部署zip文件的流程
     * @param deployPath zip文件的路径
     */
    void deployByZip(String deployPath);

    /**
     * 部署xmL文件的路径
     * @param filePath xml文件的路径（除去根目录的部分）
     * @param tempId 流程模板的id
     * @return 是否成功
     */
    boolean deployByXml(String filePath,Long tempId);

    /**
     * 启动该审批流程实例并返回实例id
     * @param process 审批流程对象
     * @return 流程实例id
     */
    String startUpProcess(Process process);

    /**
     * 获取当前审批正在进行的任务的审批人的用户名
     * @param processInstanceId
     * @return
     */
    String getCurrentAuditorByInstanceId(String processInstanceId);

    /**
     * 查看当前用户的待处理审批（处理他人的）
     * @param userId 当前用户的id
     * @return 待处理审批封装成vo的集合返回
     */
    List<ProcessVo> listMyDoingProcess(Long userId);
}
