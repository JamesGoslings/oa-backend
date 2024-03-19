package com.myPro.common.exception;

import com.myPro.common.result.ResultCodeEnum;

public class MyException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//描述信息

    public MyException(Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    //接收枚举类进行构造
    public MyException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }
    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
