package com.myPro.auth.controller;

import com.myPro.auth.service.PostService;
import com.myPro.common.result.Result;
import com.myPro.model.system.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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


    @GetMapping("all")
    public Result getAllPostWithDept(){
          List<Post> postList = postService.getAllPostWithDeptAndCount();
          return Result.ok(postList);
    }

    @GetMapping("newCode/{id}/{deptId}/{type}")
    public Result getNewPostCode(@PathVariable Long id,
                                @PathVariable Long deptId
                                ,@PathVariable Integer type){
        String newCode = postService.getNewCode(id,deptId,type);
        HashMap<String, String> map = new HashMap<>();
        map.put("newPostCode", newCode);
        return Result.ok(map);
    }
}
