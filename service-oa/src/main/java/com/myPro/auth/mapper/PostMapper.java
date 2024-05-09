package com.myPro.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.system.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper extends BaseMapper<Post> {
    /**
     * 查询出完整的post列表(含在职人数和所属部门)
     * */
    List<Post> selectAllTotalPost();
}
