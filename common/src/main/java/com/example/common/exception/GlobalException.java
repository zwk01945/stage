package com.example.common.exception;

import com.alibaba.fastjson.JSON;
import com.example.common.bean.ResultObject;
import com.example.common.bean.quartz.IcpCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base                                *
 *                                                            *
 *         File Name : GlobalException.java                   *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/7/24 17:28                       *
 *                                                            *
 *         Last Update : 2020/7/24 17:28                      *
 *                                                            *
 *------------------------------------------------------------*
 * 功能:                                                       *
 *   全局异常拦截                                               *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@RestControllerAdvice
public class GlobalException {


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
