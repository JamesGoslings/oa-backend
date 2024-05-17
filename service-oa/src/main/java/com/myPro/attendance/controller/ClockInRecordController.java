package com.myPro.attendance.controller;

import com.myPro.attendance.service.ClockInRecordService;
import com.myPro.common.result.Result;
import com.myPro.model.app.ClockInRecord;
import com.myPro.vo.attendance.ClockInRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/attendance/clockInRecord")
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

    /**
     * 拿到当前用户的本月打卡记录的总数目
     * @return 本月的考勤记录树
     * */
    @GetMapping("getCountOneMonth/{userId}")
    public Result getCountOneMonth(@PathVariable Long userId){
        int count = clockInRecordService.getCountThisMonth(userId);
        Map<String, Integer> map = new HashMap<>();
        map.put("recordCountThisMonth", count);
        return Result.ok(map);
    }

    /***
     * 获取一定时间内的全公司的·打卡率
     * @param days 待统计的天数
     * @return 全公司的·打卡率
     */
    @GetMapping("radius/{days}")
    public Result getClockInRadiusByDays(@PathVariable Integer days){
        List<ClockInRecordVo> dayRadius = clockInRecordService.getRadiusByDays(days);
        return Result.ok(dayRadius);
    }

    /**
     * 获取一些部门的打卡率
     * @param days 获取的打卡率的时间范围
     * @param idList 封装了部门的id集合
     * @return 部门们的打卡率集合
     */
    @PostMapping("deptRedius/{days}")
    public Result getDeptRadius(@PathVariable Long days,
                                @RequestBody List<Long> idList){
        List<ClockInRecordVo> deptRecordRedius  = clockInRecordService.getDeptRedius(days,idList);
        return Result.ok(deptRecordRedius);
    }
}
