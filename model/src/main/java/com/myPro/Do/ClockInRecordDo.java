package com.myPro.Do;

import lombok.Data;

import java.util.Date;

/**
 * 和数据库中的打卡记录的统计进行传输的实体类
 */
@Data
public class ClockInRecordDo {

    private Long recordNum;

    private Date clockInRecordDate;
}
