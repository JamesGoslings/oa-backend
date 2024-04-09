package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.model.process.ProcessType;
import com.myPro.process.mapper.ProcessTemplateMapper;
import com.myPro.process.mapper.ProcessTypeMapper;
import com.myPro.process.service.ProcessTemplateService;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {

    @Autowired
//    private ProcessTemplateService processTemplateService;
    private ProcessTemplateMapper processTemplateMapper;

    //查询所有的审批分类以及每个分类中所有的模板
    @Override
    public List<ProcessType> getProcessType() {
        List<ProcessType> processTypeList = baseMapper.selectList(null);
        for (ProcessType processType :processTypeList) {
            Long typeId = processType.getId();
            LambdaQueryWrapper<ProcessTemplate> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ProcessTemplate::getProcessTypeId, typeId);
//            List<ProcessTemplate> templateList = processTemplateService.list(wrapper);
            List<ProcessTemplate> templateList = processTemplateMapper.selectList(wrapper);
            processType.setProcessTemplateList(templateList);
        }
        return processTypeList;
    }
}
