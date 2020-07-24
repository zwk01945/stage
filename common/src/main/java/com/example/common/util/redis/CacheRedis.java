package com.example.common.util.redis;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: CacheRedis
 * Description: 缓存注解
 * date: 2020/7/24 13:26
 * @author Mr.zhang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface CacheRedis {
    String value() default "";
    String type() default "String";
    TimeUnit unit() default TimeUnit.SECONDS;
    long time() default -1L;
}
