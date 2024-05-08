package com.myPro.vo.system;

import lombok.Data;

/**
 * 表示父级菜单的基础信息
 * */
@Data
public class ParentMenuVo {
    private Long id;

    private String name;

    // 记录了自己及其所有父级的字符串名字
    private String totalName;
}
