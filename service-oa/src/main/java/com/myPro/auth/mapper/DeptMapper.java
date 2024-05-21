package com.myPro.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myPro.model.web.Dept;
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


    /**
     * 拿到所有的部门id
     * */
    List<Long> getAllIdList();

    /**
     * 拿到所有的部门，且获取了部门直接工作人员人数
     * */
    List<Dept> selectAllListHasMyCount();
}
