package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.model.process.ProcessType;
import com.myPro.process.mapper.ProcessTemplateMapper;
import com.myPro.process.service.ProcessService;
import com.myPro.process.service.ProcessTemplateService;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class ProcessTemplateServiceImpl extends ServiceImpl<ProcessTemplateMapper, ProcessTemplate> implements ProcessTemplateService {

    @Autowired
    private ProcessTypeService processTypeService;

    @Autowired
    private ProcessService processService;

    @Override
    public IPage<ProcessTemplate> selectPageProcessTemplate(Page<ProcessTemplate> pageParam) {
        Page<ProcessTemplate> processTemplatePage = baseMapper.selectPage(pageParam, null);

        List<ProcessTemplate> processTemplateList = processTemplatePage.getRecords();

        for (ProcessTemplate processTemplate :processTemplateList) {
            Long typeId = processTemplate.getProcessTypeId();
            ProcessType processType = processTypeService.getOne
                    (new LambdaQueryWrapper<ProcessType>().eq(ProcessType::getId, typeId));
            if(processType == null){
                continue;
            }
            processTemplate.setProcessTypeName(processType.getName());
        }

        return processTemplatePage;
    }

    @Override
    public void publish(Long id) {
        //TODO 修改模板发布状态 1：已经发布
        ProcessTemplate processTemplate = baseMapper.selectById(id);
        processTemplate.setStatus(1);
        baseMapper.updateById(processTemplate);

        //TODO 流程定义部署
        if(!StringUtils.isEmpty(processTemplate.getProcessDefinitionPath())){
            processService.deployByZip(processTemplate.getProcessDefinitionPath());
        }

    }
}
