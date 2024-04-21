package com.myPro.auth.controller;

import com.myPro.auth.service.ClockInRecordService;
import com.myPro.common.result.Result;
import com.myPro.model.app.ClockInRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/system/clockInRecord")
public class ClockInRecordController {
    @Autowired
    private ClockInRecordService clockInRecordService;

    @PostMapping("save")
    public Result saveRecord(@RequestBody ClockInRecord record){
        clockInRecordService.save(record);
        return Result.ok(record);
    }
}
