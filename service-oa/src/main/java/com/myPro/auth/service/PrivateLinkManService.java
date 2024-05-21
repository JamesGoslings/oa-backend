package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.app.PrivateLinkMan;
import com.myPro.VO.app.TotalLinkManVo;

import java.util.List;

public interface PrivateLinkManService extends IService<PrivateLinkMan> {

    /**
     * 通过userId拿到改用户对应的所有个人联系人
     * @param userId 当前用户id
     * @return 用户对应的所有个人联系人
     * */
    List<PrivateLinkMan> getLinkManListByUserId(Long userId);

    /**
     * 通过userId和模糊查询的关键字来找到对应的个人联系人然后封装好之后返回
     * @param userId 当前用户id
     * @param key 模糊查询的关键字
     * @return 符合条件的所以个人联系人的列表
     * */
    List<TotalLinkManVo> getLinkManListByUserIdAndKey(Long userId, String key);

}
