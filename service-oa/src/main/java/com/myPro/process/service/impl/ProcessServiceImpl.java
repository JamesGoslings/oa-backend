package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.utils.FileUtil;
import com.myPro.model.process.Process;
import com.myPro.model.process.ProcessRecord;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.model.system.SysUser;
import com.myPro.process.mapper.ProcessMapper;
import com.myPro.process.mapper.ProcessTemplateMapper;
import com.myPro.process.service.ProcessService;
import com.myPro.process.service.ProcessTemplateService;
import com.myPro.vo.process.ProcessQueryVo;
import com.myPro.vo.process.ProcessVo;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ProcessTemplateMapper processTemplateMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SysUserService userService;

    //审批管理列表
    @Override
    public IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, ProcessQueryVo processQueryVo) {
        return baseMapper.selectPage(pageParam, processQueryVo);
    }

    @Override
    public void deployByZip(String deployPath) {
        InputStream inputStream =
                this.getClass().getClassLoader().getResourceAsStream(deployPath);
        assert inputStream != null;
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();

    }

    @Override
    public boolean deployByXml(String filePath,Long tempId) {
        String totalPath = FileUtil.rootPath + "\\" + filePath;
        // 通过路径拿到文件名
        Pattern pattern = Pattern.compile("([^\\\\]+\\.[a-zA-Z0-9]+)$");
        Matcher matcher = pattern.matcher(filePath);
        String fileName = "";
        if (matcher.find()) {
            fileName = matcher.group(1);
        }else {
            return false;
        }
        try{
            Deployment deploy = repositoryService.createDeployment()
//                    .addClasspathResource(totalPath)
                    .addInputStream(fileName, new FileInputStream(new File(totalPath)))
                    .name("新审批流程")
                    .deploy();
            // TODO 将流程id存入模板
            ProcessTemplate template = processTemplateMapper.selectById(tempId);
            template.setProcessModelId(deploy.getId());
            processTemplateMapper.updateById(template);
        }catch (ActivitiException e){
            // 用户未将文件设置为可执行
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String startUpProcess(Process process) {
        // TODO 拿到流程的key
        String definitionKey = processTemplateMapper.selectById(process.getProcessTemplateId()).getProcessDefinitionKey();
        // 启动一个流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(definitionKey);
        return processInstance.getId();
    }

    @Override
    public String getCurrentAuditorByInstanceId(String processInstanceId) {
        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        return list.get(0).getAssignee();
    }

    @Override
    public List<ProcessVo> listMyDoingProcess(Long userId) {
        // 拿到该用户的用户名
        String username = userService.getById(userId).getUsername();
        // TODO 拿到当前所有待办任务,遍历
        return null;
    }

    @Override
    public ProcessRecord recordThisProcess(Long processId) {
        // 拿到process对象
        Process process = getById(processId);
        // 封装到记录中
        ProcessRecord record = new ProcessRecord();
        record.setProcessId(processId);
        record.setStatus(process.getStatus());
        // 拿到当前审批的管理者
        SysUser user = userService.getUserByUsername(process.getCurrentAuditor());
        record.setDoingUserId(user.getId());
        record.setDoingUserName(user.getName());

        return record;
    }
}
