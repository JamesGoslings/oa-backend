package com.myPro.model.app;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myPro.model.base.BaseEntity;
import lombok.Data;

import java.io.Serial;

/**
 * 个人联系人
 * */
@Data
@TableName("private_link_man")
public class PrivateLinkMan extends BaseEntity implements LinkMan{

    @Serial
    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("user_id")
    private Long userId;

    @TableField("relationship")
    private String relationship;

//    @TableField(exist = false)
//    private Long typeId = 2L;
//
//    @TableField(exist = false)
//    private String typeName = "个人通讯录";

}
