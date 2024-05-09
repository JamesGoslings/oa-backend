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
}
