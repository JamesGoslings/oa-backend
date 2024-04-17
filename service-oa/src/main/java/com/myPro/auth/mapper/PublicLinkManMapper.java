package com.myPro.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.app.PublicLinkMan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicLinkManMapper extends BaseMapper<PublicLinkMan> {
    /**
     * 模糊查询
     * */
    List<PublicLinkMan> getPublicLinkManListByKey(@Param("key")String key);
}
