package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.app.PublicLinkMan;
import com.myPro.vo.app.TotalLinkManVo;

import java.util.List;

public interface PublicLinkManService extends IService<PublicLinkMan> {

    /**
     * 根据关键字进行模糊查询并封装好后返回
     * @param key 模糊查询的关键字
     * @return 封装好的数据
     * */
    List<TotalLinkManVo> getLinkManListByKey(String key);
}
