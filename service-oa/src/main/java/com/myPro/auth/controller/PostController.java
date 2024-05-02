package com.myPro.auth.controller;

import com.myPro.auth.service.PostService;
import com.myPro.common.result.Result;
import com.myPro.model.system.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/system/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("allSelfAndChildren/{deptId}")
    public Result getAllPostByDeptId(@PathVariable Long deptId){
        List<Post> selfAndChildrenPostList = postService.getPostListByDeptId(deptId);
        return Result.ok(selfAndChildrenPostList);
    }
}
