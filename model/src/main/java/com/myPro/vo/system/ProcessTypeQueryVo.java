package com.myPro.vo.system;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 审批类型查询实体
 */
@Data
public class ProcessTypeQueryVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String keyword;
}
