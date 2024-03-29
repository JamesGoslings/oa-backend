package com.myPro.common.beans;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.history.HistoryLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ActivitiBeans {

    @Autowired
    DataSource dataSource;

    private ProcessEngine engine;

    @Bean
    public ProcessEngine processEngine(){
        ProcessEngineConfiguration processEngineConfig = new StandaloneProcessEngineConfiguration();
        processEngineConfig.setDatabaseSchemaUpdate("true");
        processEngineConfig.setDataSource(dataSource);
        processEngineConfig.setDbHistoryUsed(true);
        processEngineConfig.setHistoryLevel(HistoryLevel.FULL);
        processEngineConfig.setEnableProcessDefinitionInfoCache(true);
        ProcessEngine processEngine = processEngineConfig.buildProcessEngine();
        this.engine = processEngine;
        return processEngine;
    }

//    @Bean
//    public RepositoryService repositoryService(){
//        return engine.getRepositoryService();
//    }
//
//    @Bean
//    public RuntimeService runtimeService(){
//        return engine.getRuntimeService();
//    }
//
//    @Bean
//    public HistoryService historyService(){
//        return engine.getHistoryService();
//    }
//
//    @Bean
//    public TaskService taskService(){
//        return engine.getTaskService();
//    }
//
//    @Bean
//    public ManagementService managementService(){
//        return engine.getManagementService();
//    }
}
