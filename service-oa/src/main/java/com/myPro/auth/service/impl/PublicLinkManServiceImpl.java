package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.PublicLinkManMapper;
import com.myPro.auth.service.PublicLinkManService;
import com.myPro.model.app.PublicLinkMan;
import com.myPro.vo.app.TotalLinkManVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicLinkManServiceImpl extends ServiceImpl<PublicLinkManMapper, PublicLinkMan> implements PublicLinkManService {

    @Autowired
    private PublicLinkManMapper publicLinkManMapper;

    @Override
    public List<TotalLinkManVo> getLinkManListByKey(String key) {
        List<PublicLinkMan> publicLinkManList = publicLinkManMapper.getPublicLinkManListByKey(key);
        // 将publicLinkManList封装好，并为type赋值
        List<TotalLinkManVo> totalLinkManVoList = new ArrayList<>();
        for (PublicLinkMan publicLinkMan :publicLinkManList) {
            TotalLinkManVo totalLinkManVo = new TotalLinkManVo();
            totalLinkManVo.setTypeName("公共通讯录");
            totalLinkManVo.setTypeId(3L);
            totalLinkManVo.setLinkMan(publicLinkMan);
            totalLinkManVoList.add(totalLinkManVo);
        }


        return totalLinkManVoList;
    }
}
