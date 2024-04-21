package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.ClockInRecordMapper;
import com.myPro.auth.service.ClockInRecordService;
import com.myPro.model.app.ClockInRecord;
import org.springframework.stereotype.Service;

@Service
public class ClockInRecordServiceImpl extends ServiceImpl<ClockInRecordMapper, ClockInRecord> implements ClockInRecordService {
}
