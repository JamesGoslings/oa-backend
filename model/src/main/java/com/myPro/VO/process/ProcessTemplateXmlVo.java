package com.myPro.VO.process;

import lombok.Data;

/**
 * 用于保存模板的xml字符串的vo
 */
@Data
public class ProcessTemplateXmlVo {
    private String xmlStr;

    private Long tempId;
}
