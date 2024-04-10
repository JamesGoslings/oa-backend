package com.myPro.vo.app;

import lombok.Data;

/**
 * 移动端初始化的用户对象
 * */

@Data
public class SysUserVo {

    private String userId;

    private String name;

    private String post;

    private String dept;

    private String avatarUrl;
}
