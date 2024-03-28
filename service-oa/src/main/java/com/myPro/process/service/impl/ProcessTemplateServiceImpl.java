package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.model.process.ProcessType;
import com.myPro.process.mapper.ProcessTemplateMapper;
import com.myPro.process.service.ProcessTemplateService;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProcessTemplateServiceImpl extends ServiceImpl<ProcessTemplateMapper, ProcessTemplate> implements ProcessTemplateService {

    @Autowired
    private ProcessTypeService processTypeService;

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
}
