package com.myPro.attendance.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.Do.ClockInRecordDo;
import com.myPro.model.app.ClockInRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClockInRecordMapper extends BaseMapper<ClockInRecord> {
    /**
     * 拿到当前用户的最新的打卡记录
     * @param userId 当前用户的id
     * @return 最新的打卡记录
     * */
    ClockInRecord selectFirstRecord(@Param("userId") Long userId);

    /**
     * 拿到当前用户的本月打卡记录的总数目
     * @return 本月的考勤记录数
     * */
    int selectCountThisMonth(Long userId);

    /**
     * 获取一定时间内的所有打卡记录数目统计并按时间降序排列
     * @param days
     * @return 一定时间内的所有打卡记录数目
     */
    List<ClockInRecordDo> getRecordsByDays(Integer days);
}
