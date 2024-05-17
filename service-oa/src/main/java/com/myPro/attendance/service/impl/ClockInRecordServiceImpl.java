package com.myPro.attendance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.Do.ClockInRecordDo;
import com.myPro.attendance.mapper.ClockInRecordMapper;
import com.myPro.attendance.service.ClockInRecordService;
import com.myPro.auth.service.SysUserService;
import com.myPro.common.utils.DateUtil;
import com.myPro.model.app.ClockInRecord;
import com.myPro.vo.attendance.ClockInRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClockInRecordServiceImpl extends ServiceImpl<ClockInRecordMapper, ClockInRecord> implements ClockInRecordService {

    @Autowired
    private SysUserService userService;

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
    public List<ClockInRecordVo> getRadiusByDays(Integer days) {
        // TODO 拿到一定时间内所有的记录数据（时间降序排）
        List<ClockInRecordDo> records = baseMapper.getRecordsByDays(days);
//        records.forEach(System.out::println);
        // 获取公司总人数
        long userNum = userService.count();
        List<ClockInRecordVo> recordVos = new ArrayList<>();
//        Date lastDate = new Date();
        //lastDate.setDate(lastDate.getDate() + 1);
        Date date = DateUtil.clearTime(new Date());

        int j = 0;
        for (int i = 0; i < days; i++) {
            // 本次待封装数据对象
            ClockInRecordVo myVo = new ClockInRecordVo();

            ClockInRecordDo recordDo = records.get(j);
            Date recordDate = recordDo.getClockInRecordDate();
            // 没有缺少天数，直接添加数据
            if(DateUtil.isCommonDay(recordDate, date)){
                myVo.setClockInRecordDate(recordDate);
                myVo.setRecordRadius(recordDo.getRecordNum() / ((double)userNum * 2.0));
                j++;
            }else{
                // 缺天数的时候，填上0%的数据
                myVo.setRecordRadius(0.0);
                // 进行深拷贝再添加
                myVo.setClockInRecordDate(new Date(date.getTime()));
            }
            date.setDate(date.getDate() - 1);
            recordVos.add(myVo);
        }
        // 导入数据进入
        return recordVos;
    }
}
