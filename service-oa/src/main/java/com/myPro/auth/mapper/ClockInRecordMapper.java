package com.myPro.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.app.ClockInRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClockInRecordMapper extends BaseMapper<ClockInRecord> {
    /**
     * 拿到当前用户的最新的打卡记录
     * @param userId 当前用户的id
     * @return 最新的打卡记录
     * */
    ClockInRecord selectFirstRecord(@Param("userId") Long userId);
}
