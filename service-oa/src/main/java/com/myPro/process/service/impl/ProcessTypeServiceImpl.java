package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.ProcessType;
import com.myPro.process.mapper.ProcessTypeMapper;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.stereotype.Service;


@Service
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {
}
