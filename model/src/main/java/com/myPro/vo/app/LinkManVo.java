package com.myPro.vo.app;

import lombok.Data;

@SuppressWarnings("all")
@Data
public class LinkManVo {

    private String name;

    private String phone;

    private String dept;

    private String post;

    // true表示是相关负责人,false表示不是
    private boolean isHeader = false;
}
