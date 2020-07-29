package com.example.common.aop.annotation;

import java.lang.annotation.*;

/**
 * ClassName: DS
 * Description: 切换数据源用到的注解
 * date: 2020/7/21 13:16
 * @author Mr.zhang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DS {
    String value() default "master";
}
