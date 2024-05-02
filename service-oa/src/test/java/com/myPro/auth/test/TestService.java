package com.myPro.auth.test;

import com.myPro.auth.service.DeptService;
import com.myPro.auth.service.PostService;
import com.myPro.auth.service.PrivateLinkManService;
import com.myPro.auth.service.PublicLinkManService;
import com.myPro.model.app.PrivateLinkMan;
import com.myPro.model.system.Post;
import com.myPro.vo.app.LinkManVo;
import com.myPro.vo.app.SysUserVo;
import com.myPro.vo.app.TotalLinkManVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Scanner;

@SpringBootTest
public class TestService {
    @Autowired
    private DeptService deptService;

    @Autowired
    private PrivateLinkManService privateLinkManService;

    @Autowired
    private PublicLinkManService publicLinkManService;

    @Autowired
    private PostService postService;

    @Test
    public void deptServiceTest(){
//        List<LinkManVo> linkManVoList = deptService.getOriginListManByKey("李");
        List<Long> list = deptService.getDeptIdListSelfAndChildren(3L);
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

    @Test
    public void PrivateLinkManServiceTest(){
        List<TotalLinkManVo> list = privateLinkManService.getLinkManListByUserIdAndKey(0L, "大");
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

    @Test
    public void PublicLinkManServiceTest(){
        List<TotalLinkManVo> list = publicLinkManService.getLinkManListByKey("爷");
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

    @Test
    public void PostServiceTest(){
        List<Post> list = postService.getPostListByDeptId(0L);
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

}
