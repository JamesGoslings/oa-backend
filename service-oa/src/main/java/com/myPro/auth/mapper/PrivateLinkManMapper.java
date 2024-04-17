package com.myPro.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.app.PrivateLinkMan;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateLinkManMapper extends BaseMapper<PrivateLinkMan> {

    /**
     * 通过userId和模糊查询的关键字来找到对应的个人联系人并返回
     * @param userId 当前用户id
     * @param key 模糊查询的关键字
     * @return 符合条件的所以个人联系人的列表
     * */
    List<PrivateLinkMan> getLinkManListByUserIdAndKey(@Param("userId") Long userId,@Param("key") String key);
}
