package com.myPro.process.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myPro.common.result.Result;
import com.myPro.model.process.ProcessType;
import com.myPro.process.service.ProcessTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/admin/process/processType")
public class ProcessTypeController {

    @Autowired
    private ProcessTypeService processTypeService;


    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit) {
        Page<ProcessType> pageParam = new Page<>(page,limit);
        return Result.ok(processTypeService.page(pageParam));
    }

    @GetMapping("findAll")
    public Result getAll() {
        return Result.ok(processTypeService.list());
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        ProcessType processType = processTypeService.getById(id);
        return Result.ok(processType);
    }


    @PostMapping("save")
    public Result save(@RequestBody ProcessType processType) {
        processTypeService.save(processType);
        return Result.ok();
    }

    @PutMapping("update")
    public Result updateById(@RequestBody ProcessType processType) {
        processTypeService.updateById(processType);
        return Result.ok();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        processTypeService.removeById(id);
        return Result.ok();
    }
}
