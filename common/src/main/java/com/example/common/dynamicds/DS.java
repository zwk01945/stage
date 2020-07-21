package com.example.common.dynamicds;

import java.lang.annotation.*;

/**
 * ClassName: DS
 * Description:
 * date: 2020/7/21 13:16
 *
 * @author zyl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface DS {
    String value() default "master";
}
