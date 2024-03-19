package com.myPro.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(201, "失败"),

    LOGIN_ERROR(205,"认证失败"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限");

    private final Integer code;

    private final String message;

}
