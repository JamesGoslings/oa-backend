package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.PublicLinkManMapper;
import com.myPro.auth.service.PublicLinkManService;
import com.myPro.model.app.PublicLinkMan;
import org.springframework.stereotype.Service;

@Service
public class PublicLinkManServiceImpl extends ServiceImpl<PublicLinkManMapper, PublicLinkMan> implements PublicLinkManService {
}
