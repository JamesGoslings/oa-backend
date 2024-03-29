package com.myPro.process.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.process.Process;
import com.myPro.vo.process.ProcessQueryVo;
import com.myPro.vo.process.ProcessVo;

public interface ProcessService extends IService<Process> {
    IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, ProcessQueryVo processQueryVo);
}
