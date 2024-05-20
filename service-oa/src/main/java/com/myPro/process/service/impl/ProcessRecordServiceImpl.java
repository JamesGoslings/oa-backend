package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.ProcessRecord;
import com.myPro.process.mapper.ProcessRecordMapper;
import com.myPro.process.service.ProcessRecordService;
import org.springframework.stereotype.Service;

@Service
public class ProcessRecordServiceImpl extends ServiceImpl<ProcessRecordMapper, ProcessRecord> implements ProcessRecordService {
}
