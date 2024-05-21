package com.myPro.auth.test;

import com.myPro.attendance.service.ClockInRecordService;
import com.myPro.attendance.utils.RecordInitDataUtil;
import com.myPro.auth.service.*;
import com.myPro.model.web.Dept;
import com.myPro.model.web.Post;
import com.myPro.VO.app.TotalLinkManVo;
import com.myPro.VO.attendance.ClockInRecordVo;
import com.myPro.VO.system.ParentMenuVo;
import com.myPro.VO.system.SysUserWebVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
    private ClockInRecordService clockInRecordService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private RecordInitDataUtil recordInitDataUtil;

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

    @Test
    public void getTotalPostListTest(){
        List<Post> postList = postService.getAllPostWithDeptAndCount();
        System.out.println("==========================>>>>>>>>>>>>");
        postList.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }

    @Test
    public void getNewCodeTest(){
        String newCode = postService.getNewCode(15L, 11L, 0);
        System.out.println("=======================================>>>>>>>>");
        System.out.println("newCode = " + newCode);
        System.out.println("=======================================>>>>>>>>");
    }

    @Test
    public void getTotalPostsByKeywordTest(){
        List<Post> list = postService.getPostListWithDeptAndCountByKeyword(null);
        System.out.println("==========================>>>>>>>>>>>>");
        list.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }

    @Test
    public void getAllTreeDeptListTest(){
        List<Dept> treeDeptList = deptService.getAllTreeDeptList();
        System.out.println("==========================>>>>>>>>>>>>");
        treeDeptList.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }

    @Test
    public void getRecordsByDaysTest(){
        List<ClockInRecordVo> radiusByDays = clockInRecordService.getRadiusByDays(15);
        System.out.println("==========================>>>>>>>>>>>>");
        radiusByDays.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }

    @Test
    public void RecordUtilTest(){
        recordInitDataUtil.initData();
    }

    @Test
    public void dateTest(){
        Date date = new Date();
        date.setSeconds(0);
        date.setHours(9);
        date.setMinutes(0);
        //        System.out.println(date);
//        System.out.println(DateUtil.isPreviousDay(date, new Date()));
    }

    @Test
    public void getDeptRediusTest(){
        ArrayList<Long> idList = new ArrayList<>();
        idList.add(11L);
        idList.add(1L);
        idList.add(10L);
        List<ClockInRecordVo> deptRedius = clockInRecordService.getDeptRedius(7L, idList);
        System.out.println("==========================>>>>>>>>>>>>");
        deptRedius.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }

    @Test
    public void getNotUserInDeptTest(){
        List<SysUserWebVo> notUserInDept = clockInRecordService.getNotUserInDept(15L, 0L, 0L);
        System.out.println("==========================>>>>>>>>>>>>");
        notUserInDept.forEach(System.out::println);
        System.out.println("==========================>>>>>>>>>>>>");
    }
}
