package com.myPro.auth.service;

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
}
