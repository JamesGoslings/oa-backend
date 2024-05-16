package com.myPro.process.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.process.ProcessTemplate;

import java.util.List;

public interface ProcessTemplateService extends IService<ProcessTemplate> {
    IPage<ProcessTemplate> selectPageProcessTemplate(Page<ProcessTemplate> pageParam);

    IPage<ProcessTemplate> selectPageProcessTemplate(Page<ProcessTemplate> pageParam, LambdaQueryWrapper<ProcessTemplate> wrapper);

    void publish(Long id);

    /**
     * 获取所有的模板带类型名
      * @return 所有的模板带类型名
     */
    List<ProcessTemplate> getAllTemplate();
}
