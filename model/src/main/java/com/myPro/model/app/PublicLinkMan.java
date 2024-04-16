package com.myPro.model.app;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myPro.model.base.BaseEntity;
import lombok.Data;


/**
 * 公共联系人
 * */

@Data
@TableName("public_link_man")
public class PublicLinkMan extends BaseEntity {

    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;
}
