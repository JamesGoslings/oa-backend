package com.myPro.process.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myPro.common.exception.MyException;
import com.myPro.common.result.Result;
import com.myPro.model.process.ProcessTemplate;
import com.myPro.process.service.ProcessTemplateService;

import com.myPro.vo.system.ProcessTemplateQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
@RestController
@RequestMapping("/admin/process/processTemplate")
public class ProcessTemplateController {

    @Autowired
    private ProcessTemplateService processTemplateService;

    @GetMapping("{page}/{limit}")
    public Result page(@PathVariable Long page,
                       @PathVariable Long limit,
                       ProcessTemplateQueryVo queryVo){
        LambdaQueryWrapper<ProcessTemplate> wrapper = new LambdaQueryWrapper<>();
        String keyword = queryVo.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            wrapper.or()
                    .like(ProcessTemplate::getName, keyword)
                    .or()
                    .like(ProcessTemplate::getDescription, keyword);
        }
        Page<ProcessTemplate> pageParam = new Page<>(page, limit);
        //根据类型id把审批类型对应名称查询出来放到返回的page中
        IPage<ProcessTemplate> pageModel =
                processTemplateService.selectPageProcessTemplate(pageParam,wrapper);
        return Result.ok(pageModel);
    }

    @GetMapping("all")
    public Result getAllTemlate(){
        return Result.ok(processTemplateService.getAllTemplate());
    }
    //部署流程定义(发布)
    @GetMapping("/publish/{id}")
    public Result publish(@PathVariable Long id){
        //修改模板发布状态 1：已经发布
        processTemplateService.publish(id);
        return Result.ok();
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

    @PostMapping("uploadProcessDefinition")
    public Result uploadProcessDefinition(MultipartFile file) throws FileNotFoundException {
         //TODO 获取classes目录路径
        String path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();
        //TODO 设置上传文件夹
        File tempFile = new File(path + "/processes/");
        if(!tempFile.exists()){
            tempFile.mkdirs();
        }
        //todo 创建空文件，实现文件写入
        String fileName = file.getOriginalFilename();
        File zipFile = new File(path + "/processes/" + fileName);

        //todo 保存文件
        try {
            file.transferTo(zipFile);
        } catch (IOException e) {
            throw new MyException(503,"保存文件失败~");
        }

        Map<String, Object> map = new HashMap<>();
        //todo 根据上传地址后续部署流程定义，文件名称为流程定义的默认key
        map.put("processDefinitionPath", "processes/" + fileName);
        map.put("processDefinitionKey", fileName.substring(0, fileName.lastIndexOf(".")));
        return Result.ok(map);
    }

    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList){
        if(processTemplateService.removeByIds(idList)){
            return Result.ok();
        }
        return Result.fail();
    }

}
