package com.myPro.vo.system;

import com.myPro.model.system.SysRole;
import com.myPro.vo.app.SysUserVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 封装返回web端的user的分页查询的单个对象
 * */
@Data
public class SysUserWebVo extends SysUserVo {

    private Long deptId;

    private Long postId;

    private String username;

    private Integer status;

    private Date updateTime;

    private List<SysRole> roleList;

    private  Boolean isChoose = false;
}
