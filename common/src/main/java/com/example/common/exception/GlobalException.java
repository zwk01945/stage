package com.example.common.exception;


import com.example.common.bean.ResultObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: GlobalException
 * Description: 全局异常拦截
 * date: 2020/7/16 18:49
 * @author zwk
 */
@RestControllerAdvice
public class GlobalException {
    /**
     * Description : 全局异常捕捉处理
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Object customerHandler(MyException e) {
        return new ResultObject(10000);
    }

    @ExceptionHandler(RuntimeException.class)
    public Object nullPointHandler(RuntimeException e) {
        e.printStackTrace();
        return new ResultObject(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(Exception.class)
    public Object ExceptionPointHandler(Exception e) {
        e.printStackTrace();
        return new ResultObject(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
