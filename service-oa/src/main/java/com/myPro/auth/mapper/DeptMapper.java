package com.myPro.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.system.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 根据key值查询，返回detail
     * */
//    List<LeaderDetail> getLeaderDetailListByKey (String  key);
    List<Dept> getDeptListByKey (@Param("key") String  key);


}
