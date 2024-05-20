package com.myPro.vo.app;

import lombok.Data;

import java.util.Date;

/**
 * 移动端初始化的用户对象
 * */

@Data
public class SysUserVo {

    private Long userId;

    private String username;

    private String name;

    private String post;

    private String dept;

    private String avatarUrl;

    private String phone;

    private Date createTime;
}
