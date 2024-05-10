package com.myPro.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myPro.auth.service.PostService;
import com.myPro.common.result.Result;
import com.myPro.model.system.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("save")
    public Result save(@RequestBody Post post){
        postService.save(post);
        return Result.ok();
    }

    @PutMapping("update")
    public Result update(@RequestBody Post post){
        postService.updateById(post);
        return Result.ok();
    }

    @GetMapping("getByKey/{keyword}")
    public Result getTotalPostListByKeyword(@PathVariable String keyword){
        List<Post> postList = null;
        if(StringUtils.isEmpty(keyword)){
            postList = postService.getAllPostWithDeptAndCount();
        }else {
            postList = postService.getPostListWithDeptAndCountByKeyword(keyword);
        }
        return Result.ok(postList);
    }

    @DeleteMapping("remove/{postId}")
    public Result removePostOne(@PathVariable Long postId){
        postService.removeById(postId);
        return Result.ok();
    }
}
