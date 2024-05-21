package com.myPro.VO.system;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class TestVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String testName;
}
