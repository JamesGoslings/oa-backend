package com.myPro.VO.app;

import com.myPro.model.app.LinkMan;
import lombok.Data;

/**
 * 用于privateLinkMan和publicLinkMan的统一返回
 * */

@Data
public class TotalLinkManVo {
    private Long typeId;

    private String typeName;

    private String deptName;

    private final boolean isChoose = false;

    private LinkMan linkMan;

}
