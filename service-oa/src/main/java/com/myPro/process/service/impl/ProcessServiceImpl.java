package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.common.utils.FileUtil;
import com.myPro.model.process.Process;
import com.myPro.process.mapper.ProcessMapper;
import com.myPro.process.service.ProcessService;
import com.myPro.vo.process.ProcessQueryVo;
import com.myPro.vo.process.ProcessVo;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {

    @Autowired
//    private RepositoryService repositoryService;
    private ProcessEngine processEngine;

//    @Autowired
//    private RepositoryService repositoryService;

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

        System.out.println("=============================================");
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());
        System.out.println("=============================================");

    }

    @Override
    public boolean deployByXml(String filePath) {
//        String totalPath = FileUtil.rootPath + "\\" + filePath;
        String totalPath = filePath;
        RepositoryService repositoryService = processEngine.getRepositoryService();
        try{
            Deployment deploy = repositoryService.createDeployment()
                    .addClasspathResource(totalPath)
                    .name("新审批流程")
                    .deploy();
            System.out.println("=============================================");
            System.out.println(deploy.getId());
            System.out.println(deploy.getKey());
            System.out.println(deploy.getName());
            System.out.println("=============================================");
        }catch (ActivitiException e){
            // 用户未将文件设置为可执行
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
