package com.myPro.common.exception;

import com.myPro.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings("all")
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> error(Exception e){
        e.printStackTrace();
        return Result.fail(e.getMessage()).message("执行了全局异常处理...");
    }

    //空指针异常处理
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e){
        e.printStackTrace();
        return Result.fail(e.getMessage()).message("执行了空指针异常处理...");
    }
}
