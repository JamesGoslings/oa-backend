package com.myPro.process.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myPro.common.result.Result;
import com.myPro.model.process.ProcessType;
import com.myPro.process.service.ProcessTypeService;
import com.myPro.vo.system.ProcessTypeQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/admin/process/processType")
public class ProcessTypeController {

    @Autowired
    private ProcessTypeService processTypeService;


    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        ProcessTypeQueryVo queryVo) {
        LambdaQueryWrapper<ProcessType> wrapper = new LambdaQueryWrapper<>();
        String keyword = queryVo.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            wrapper.or()
                    .like(ProcessType::getName, keyword)
                    .or()
                    .like(ProcessType::getDescription, keyword);
        }
        Page<ProcessType> pageParam = new Page<>(page,limit);
        return Result.ok(processTypeService.page(pageParam,wrapper));
    }

    @GetMapping("all")
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

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        if(processTypeService.removeByIds(idList)){
            return Result.ok();
        }
        return Result.fail();
    }
}
