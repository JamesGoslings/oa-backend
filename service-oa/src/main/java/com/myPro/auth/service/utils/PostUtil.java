package com.myPro.auth.service.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.PostService;
import com.myPro.model.web.Dept;
import com.myPro.model.web.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理岗位模块的工具类
 * */
@Component
public class PostUtil {
    @Autowired
    private PostService postService;

    @Autowired
    private DeptService deptService;

    // 岗位类型信息 0: 其他; 1: 管理岗; 2: 技术岗位; 3: 销售岗; 4: 研发岗;
    public final static String[] types = {"E","M","T","S","R"};
    /***
     * 初始化岗位的code信息
     */
    public void initPostCodeAll(){
        List<Post> posts = postService.list();
        for (Post post : posts) {
            // 拿到对应的部门的code
            String deptCode = deptService.getOne(
                    new LambdaQueryWrapper<Dept>().eq(Dept::getId, post.getDeptId())).getDeptCode();
            // 拿到当前序号
            long count = postService.count(
                    new LambdaQueryWrapper<Post>()
                            .eq(Post::getDeptId, post.getDeptId())
                            .ne(Post::getPostCode, "?"));
            count++;
            String countStr = count < 10 ? "0" + count : count + "";
            post.setPostCode(deptCode + types[post.getType()] + countStr);
            postService.updateById(post);
        }
    }
}
