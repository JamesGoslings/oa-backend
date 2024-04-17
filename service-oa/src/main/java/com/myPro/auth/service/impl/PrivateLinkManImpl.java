package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.PrivateLinkManMapper;
import com.myPro.auth.service.PrivateLinkManService;
import com.myPro.model.app.PrivateLinkMan;
import com.myPro.vo.app.TotalLinkManVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivateLinkManImpl extends ServiceImpl<PrivateLinkManMapper, PrivateLinkMan> implements PrivateLinkManService {

    @Autowired
    private PrivateLinkManMapper linkManMapper;

    @Override
    public List<PrivateLinkMan> getLinkManListByUserId(Long userId) {
        LambdaQueryWrapper<PrivateLinkMan> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PrivateLinkMan::getUserId, userId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<TotalLinkManVo> getLinkManListByUserIdAndKey(Long userId, String key) {
        List<PrivateLinkMan> linkManList = linkManMapper.getLinkManListByUserIdAndKey(userId, key);
        List<TotalLinkManVo> totalLinkManVoList = new ArrayList<>();

        // 给TotalLinkManVo的type赋值
        for (PrivateLinkMan privateLinkMan : linkManList) {
            TotalLinkManVo totalLinkManVo = new TotalLinkManVo();
            totalLinkManVo.setTypeId(2L);
            totalLinkManVo.setTypeName("个人通讯录");
            totalLinkManVo.setLinkMan(privateLinkMan);
            totalLinkManVoList.add(totalLinkManVo);
        }
        return  totalLinkManVoList;
    }
}
