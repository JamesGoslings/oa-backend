package com.myPro.attendance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.app.ClockInRecord;

import java.util.List;

public interface ClockInRecordService extends IService<ClockInRecord> {
    /**
     * 根据userId获取该用户的打卡记录，并根据打卡时间来进行降序排列返回
     * @param userId 该用户的id
     * */
    List<ClockInRecord> getListByUserIdDesc(Long userId);

    /**
     * 拿到当前用户的最新的打卡记录
     * @param userId 当前用户的id
     * @return 最新的打卡记录
     * */
    ClockInRecord getFirstRecord(Long userId);

    /**
     * 拿到当前用户的本月打卡记录的总数目
     * @return 本月的考勤记录数
     * */
    int getCountThisMonth(Long userId);

    /**
     * 获取一定时间内对的打卡率
     * @param days
     * @return
     */
    Double[] getRadiusByDays(Integer days);
}
