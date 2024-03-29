package com.myPro.process.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.model.process.Process;
import com.myPro.process.mapper.ProcessMapper;
import com.myPro.process.service.ProcessService;
import com.myPro.vo.process.ProcessQueryVo;
import com.myPro.vo.process.ProcessVo;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {

    //审批管理列表
    @Override
    public IPage<ProcessVo> selectPage(Page<ProcessVo> pageParam, ProcessQueryVo processQueryVo) {
        return baseMapper.selectPage(pageParam, processQueryVo);
    }
}
