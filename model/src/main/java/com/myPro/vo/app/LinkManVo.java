package com.myPro.vo.app;

import com.myPro.model.system.Dept;
import lombok.Data;

import java.util.List;

/**
 *用于与移动端通讯模块的传输
 * */

@SuppressWarnings("all")
@Data
public class LinkManVo {

    private String deptName;

    private SysUserVo sysUserVo;

    // true表示是相关负责人,false表示不是
    private boolean isHeader = false;

    private List<LinkManVo> children;
}
