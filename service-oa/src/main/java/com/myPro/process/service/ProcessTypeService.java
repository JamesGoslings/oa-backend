package com.myPro.process.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.process.ProcessType;

import java.util.List;

public interface ProcessTypeService extends IService<ProcessType> {
    List<ProcessType> getProcessType();
}
