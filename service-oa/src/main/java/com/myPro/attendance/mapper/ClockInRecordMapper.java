package com.myPro.attendance.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.DO.ClockInRecordDo;
import com.myPro.model.app.ClockInRecord;
import com.myPro.model.web.SysUser;
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

    /**
     * 获取单个部门的打卡记录数目
     * @param days 记录的时间（距离今天多少天）
     * @param deptId 部门的id
     * @return 该部门的打卡记录数目和部门名称
     */
    ClockInRecordDo getOneDeptCountByDays(Long days, Long deptId);

    /**
     * 判断该用户在某天是否完成了某种打卡
     * @param userId  用户的id
     * @param type 打卡的类型
     * @param days 统计天数距离今天的天数
     * @return 如果打卡了就返回null，否则返回该用户的信息
     */
    SysUser getUserRecordNumInOneDay(Long userId, Long type, Long days);
}
