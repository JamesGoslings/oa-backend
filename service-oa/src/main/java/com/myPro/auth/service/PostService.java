package com.myPro.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myPro.model.system.Post;

import java.util.List;

public interface PostService extends IService<Post> {
    /**
     * 根据部门id拿到该部门直接的岗位以及其子部门的岗位
     * @param deptId 根部门的id
     * @return 所有子部门及直属岗位构成的普通list
     * */
    List<Post> getPostListByDeptId(Long deptId);

    /**
     * 获取全部的岗位，并且给岗位附上对应的部门的名称以及岗位在职人数
     * @return 全部的岗位的list
     */
    List<Post> getAllPostWithDeptAndCount();

    /**
     * 根据新的所属部门和新的类型生成新的编码
     * @param id 该岗位的id(如果id为空，就认定为新建的code码)
     * @param deptId 新部门的id
     * @param type  新的岗位类型
     * @return 新的岗位编码
     */
    String getNewCode(Long id,Long deptId, Integer type);

    /**
     * 根据关键词查到对应的岗位附有人数部门信息
     * @param keyword 查询使用的关键词
     * @return 条件查询出来且处理好了的岗位的集合
     */
    List<Post> getPostListWithDeptAndCountByKeyword(String keyword);
}
