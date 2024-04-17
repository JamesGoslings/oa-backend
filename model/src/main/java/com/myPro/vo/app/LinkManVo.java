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

    private Long typeId;

    private String typeName;

    private String deptName;

    private SysUserVo leader;

//    // true表示是相关负责人,false表示不是
//    private boolean isHeader = false;

    private List<LinkManVo> children;

    // 用于确定部门的层级关系，最终封装时，将其清空再返回
    private Long deptId;

    // 用于确定部门的层级关系，最终封装时，将其清空再返回
    private Long parentId;

    // 确定当前目录的层数
    private Integer floor = 0;

    private  boolean isChoose = false;
}
