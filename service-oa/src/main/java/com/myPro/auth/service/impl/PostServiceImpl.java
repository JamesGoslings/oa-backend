package com.myPro.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myPro.auth.mapper.PostMapper;
import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.PostService;
import com.myPro.auth.service.utils.PostUtil;
import com.myPro.model.system.Dept;
import com.myPro.model.system.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Autowired
    private DeptService deptService;

    @Override
    public List<Post> getPostListByDeptId(Long deptId) {
        //TODO 拿到该部门id及其子部门的id的list
        List<Long> deptIdList = deptService.getDeptIdListSelfAndChildren(deptId);
        //TODO 根据id列表拿到所有对应的岗位
        List<Post> postList = new ArrayList<>();
        for (Long aDeptId : deptIdList) {
            LambdaQueryWrapper<Post> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Post::getDeptId, aDeptId);
            // 表示这一个部门直属的岗位的list
            List<Post> selfPosts = list(wrapper);
            postList.addAll(selfPosts);
        }

        return postList;
    }

    @Override
    public List<Post> getAllPostWithDeptAndCount() {
        List<Post> totalPostList =  baseMapper.selectAllTotalPost();
        return totalPostList;
    }

    @Override
    public String getNewCode(Long id,Long deptId, Integer type) {
        // 拿到所属部门的部门编码
        String deptCode = deptService.getById(deptId).getDeptCode();
        // 拿到序号部分
        String oldCode = getById(id).getPostCode();
        String num = oldCode.substring(oldCode.length() - 2);
        // 拿到类型字符
        String t = PostUtil.types[type];
        return deptCode + t + num;
    }
}
