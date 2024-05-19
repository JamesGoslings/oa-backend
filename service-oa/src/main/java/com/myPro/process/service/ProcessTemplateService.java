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

    /**
     * 通过zip文件来发布审批
     * @param id 模板的id
     */
    void publish(Long id);

    /**
     * 通过xml文件来发布审批
     * @param id 模板的id
     * @return 是否成功
     */
    boolean publishByXml(Long id);

    /**
     * 获取所有的模板带类型名
      * @return 所有的模板带类型名
     */
    List<ProcessTemplate> getAllTemplate();
}
