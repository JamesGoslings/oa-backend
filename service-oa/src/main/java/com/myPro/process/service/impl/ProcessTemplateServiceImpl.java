package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.process.mapper.ProcessTemplateMapper;
import com.myPro.process.service.ProcessTemplateService;
import org.springframework.stereotype.Service;


@Service
public class ProcessTemplateServiceImpl extends ServiceImpl<ProcessTemplateMapper, ProcessTemplate> implements ProcessTemplateService {
}
