package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.app.PrivateLinkMan;

import java.util.List;

public interface PrivateLinkManService extends IService<PrivateLinkMan> {

    /**
     * 通过userId拿到改用户对应的所有个人联系人
     * @param userId 当前用户id
     * @return 用户对应的所有个人联系人
     * */
    List<PrivateLinkMan> getLinkManListByUserId(Long userId);
}
