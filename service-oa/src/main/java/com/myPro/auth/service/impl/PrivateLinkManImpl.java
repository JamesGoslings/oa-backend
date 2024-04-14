package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.PrivateLinkManMapper;
import com.myPro.auth.service.PrivateLinkManService;
import com.myPro.model.app.PrivateLinkMan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateLinkManImpl extends ServiceImpl<PrivateLinkManMapper, PrivateLinkMan> implements PrivateLinkManService {
    @Override
    public List<PrivateLinkMan> getLinkManListByUserId(Long userId) {
        LambdaQueryWrapper<PrivateLinkMan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PrivateLinkMan::getUserId, userId);
        return baseMapper.selectList(wrapper);
    }
}
