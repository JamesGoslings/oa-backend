package com.myPro.auth.controller;

import com.myPro.auth.service.ClockInRecordService;
import com.myPro.common.result.Result;
import com.myPro.model.app.ClockInRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/system/clockInRecord")
public class ClockInRecordController {
    @Autowired
    private ClockInRecordService clockInRecordService;

    /**
     * 保存一条打卡记录
     * @param record 接收的记录对象
     * */
    @PostMapping("save")
    public Result saveRecord(@RequestBody ClockInRecord record){
        clockInRecordService.save(record);
        return Result.ok(record);
    }

    /**
     * 拿到当前用户的所有打卡记录并且按时间从早到晚排序
     * @return 排完序的list
     * */
    @GetMapping("getAll/{userId}")
    public Result getAll(@PathVariable Long userId){
        List<ClockInRecord> recordList = clockInRecordService.getListByUserIdDesc(userId);
        return Result.ok(recordList);
    }

    /**
     * 拿到当前用户最新的一条打卡记录
     * @return 最新打卡记录
     * */
    @GetMapping("getFirst/{userId}")
    public Result getFirst(@PathVariable Long userId){
        ClockInRecord record = clockInRecordService.getFirstRecord(userId);
        return Result.ok(record);
    }
}
