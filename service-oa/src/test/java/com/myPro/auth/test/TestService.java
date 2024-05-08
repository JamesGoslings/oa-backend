package com.myPro.auth.test;

import com.myPro.auth.service.*;
import com.myPro.model.app.PrivateLinkMan;
import com.myPro.model.system.Post;
import com.myPro.vo.app.LinkManVo;
import com.myPro.vo.app.SysUserVo;
import com.myPro.vo.app.TotalLinkManVo;
import com.myPro.vo.system.ParentMenuVo;
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

    @Autowired
    private SysMenuService menuService;
    @Test
    public void deptServiceTest(){
//        List<LinkManVo> linkManVoList = deptService.getOriginListManByKey("李");
        List<Long> list = deptService.getDeptIdListSelfAndChildren(3L);
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

    @Test
    public void privateLinkManServiceTest(){
        List<TotalLinkManVo> list = privateLinkManService.getLinkManListByUserIdAndKey(0L, "大");
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

    @Test
    public void publicLinkManServiceTest(){
        List<TotalLinkManVo> list = publicLinkManService.getLinkManListByKey("爷");
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }

    @Test
    public void postServiceTest(){
        List<Post> list = postService.getPostListByDeptId(0L);
        System.out.println("==========================================");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }
    @Test
    public void menuServiceTest() {
        List<Long> ids = menuService.getMyIdsWithoutChildren(2L);
        ids.forEach(System.out::println);
    }

    @Test
    public void parentMenuService(){
        System.out.println("==========================>>>>>>>>>>>>");
        List<ParentMenuVo> allParentMenuVo = menuService.getAllParentMenuVo();
        allParentMenuVo.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }

}
