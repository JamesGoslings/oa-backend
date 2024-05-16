package com.myPro.vo.attendance;

import lombok.Data;

import java.util.Date;

/**
 * 用于和前端进行数据输出的实体类
 */
@Data
public class ClockInRecordVo {
    private Double recordRadius;

    private Date clockInRecordDate;
}
