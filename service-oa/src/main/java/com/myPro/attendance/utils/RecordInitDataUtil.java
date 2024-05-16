package com.myPro.attendance.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.myPro.attendance.service.ClockInRecordService;
import com.myPro.auth.service.SysUserService;
import com.myPro.model.app.ClockInRecord;
import com.myPro.model.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 用于初始化和生成打卡记录的数据的工具类
 */

@Component
public class RecordInitDataUtil {

    @Autowired
    private ClockInRecordService recordService;

    @Autowired
    private SysUserService userService;

    public void initData(){
        // TODO 初始化这个月的所有员工的上下班打卡数据(并制造部分不打卡情况)
        // 拿到所有用户的id
        List<Long> userIds = userService.listObjs(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getIsDeleted, 0)
                .select(SysUser::getId));
        for (int i = 1; i <= new Date().getDate(); i++) {
            for (int j = 0; j < 2; j++) {
                for (Long userId : userIds) {
                    ClockInRecord record = getClockInRecord(userId, j, i);
                    // 制造不打卡情况
                    int randNum = new Random().nextInt(20);
                    if(userId != randNum){
                        recordService.save(record);
                    }
                }
            }
        }
    }

    private static ClockInRecord getClockInRecord(Long userId, int j, int i) {
        ClockInRecord record = new ClockInRecord();
        record.setType(j);
        record.setWay(0);
        record.setUserId(userId);
        Date date = new Date();
        date.setDate(i);
        if(j == 0){
            date.setHours(8);
            date.setMinutes(40);
        }else {
            date.setHours(18);
            date.setMinutes(11);
        }
        record.setClockInTime(date);
        return record;
    }

}
