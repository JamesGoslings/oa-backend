package com.myPro.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.attendance.mapper.ClockInRecordMapper;
import com.myPro.attendance.service.ClockInRecordService;
import com.myPro.model.app.ClockInRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockInRecordServiceImpl extends ServiceImpl<ClockInRecordMapper, ClockInRecord> implements ClockInRecordService {
    @Override
    public List<ClockInRecord> getListByUserIdDesc(Long userId) {
        LambdaQueryWrapper<ClockInRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClockInRecord::getUserId, userId);
        wrapper.orderByDesc(ClockInRecord::getClockInTime);
        return list(wrapper);
    }

    @Override
    public ClockInRecord getFirstRecord(Long userId) {
        return baseMapper.selectFirstRecord(userId);
    }

    @Override
    public int getCountThisMonth(Long userId) {
        return baseMapper.selectCountThisMonth(userId);
    }

    @Override
    public Double[] getRadiusByDays(Integer days) {
        // TODO 拿到一定时间内所有的记录数据
        List<ClockInRecord> records = baseMapper.getRecordsByDays(days);

        return new Double[0];
    }
}
