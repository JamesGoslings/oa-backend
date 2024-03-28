package com.myPro.process.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myPro.common.result.Result;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.process.service.ProcessTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@SuppressWarnings("all")
@RestController
@RequestMapping("/admin/process/processTemplate")
public class ProcessTemplateController {

    @Autowired
    private ProcessTemplateService processTemplateService;

    @GetMapping("{page}/{limit}")
    public Result page(@PathVariable Long page,
                       @PathVariable Long limit){
        Page<ProcessTemplate> pageParam = new Page<>(page, limit);
        return Result.ok(processTemplateService.page(pageParam));
    }

    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        ProcessTemplate processTemplate = processTemplateService.getById(id);
        return Result.ok(processTemplate);
    }

    @PostMapping("save")
    public Result save(@RequestBody ProcessTemplate processTemplate){
        processTemplateService.save(processTemplate);
        return Result.ok();
    }

    @PutMapping("update")
    public Result update(@RequestBody ProcessTemplate processTemplate){
        processTemplateService.updateById(processTemplate);
        return Result.ok();
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        processTemplateService.removeById(id);
        return Result.ok();
    }

}
