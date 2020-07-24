package com.example.common.util.redis;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base                                *
 *                                                            *
 *         File Name : RedisAspect.java                       *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/7/24 17:28                       *
 *                                                            *
 *         Last Update : 2020/7/24 17:28                      *
 *                                                            *
 *------------------------------------------------------------*
 * 功能:                                                       *
 *   redis切面类，在被@CacheRedis标注的方法都会进入到该类下的方法     *
 *   只可以标注在方法上，可以设置超大数组key和过期时间以及单位以及      *
 *   存放的数据类型                                              *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@Aspect
@Component
@Order(1)
public class RedisAspect {

    public static Logger logger = LoggerFactory.getLogger(RedisAspect.class);

    StringRedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Around("@within(com.example.common.util.redis.CacheRedis) || @annotation(com.example.common.util.redis.CacheRedis)")
    public Object beforeSwitchCs(ProceedingJoinPoint point) {
        logger.info("进去redis aspect切面");
        Object o = null;
        try {
            //获取需要访问的类的Class
            Class<?> clazz = point.getTarget().getClass();
            //获取需要访问的方法名
            String methodName = point.getSignature().getName();
            //获取参数
            Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
            String cache = "";String type = "";
            TimeUnit unit = null; long time = -1L;
            Method method = clazz.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(CacheRedis.class)) {
                CacheRedis cr = method.getAnnotation(CacheRedis.class);
                cache = cr.value();
                type = cr.type();
                unit = cr.unit();
                time = cr.time();
            }
            Class<?> returnType = method.getReturnType();
            o = setCache(type, unit,time,point,returnType,cache);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
       return o;
    }

    private Object setCache(String type,TimeUnit unit,long time,ProceedingJoinPoint point,Class<?> returnType, String... cacheName) throws Throwable {
        Object result = null;
        String key = cacheName[0];
            if (type.toLowerCase().equals("string")) {
                synchronized (this) {
                    result = redisTemplate.opsForValue().get(key);
                    if (result == null) {
                        logger.info("从数据库读取数据");
                        result = point.proceed();
                        redisTemplate.opsForValue().setIfAbsent(key, JSON.toJSONString(result),time,unit);
                    } else {
                        logger.info("从缓存库读取数据");
                        String redisCache = (String) result;
                        result = JSON.parseObject(redisCache,returnType);
                    }
                }
            }
        return result;
    }

//    @After("@within(com.example.common.util.redis.CacheRedis) || @annotation(com.example.common.util.redis.CacheRedis)")
//    public void afterSwitchDS (JoinPoint point) {
//        DataSourceContextHolder.clearDbType();
//        logger.info("数据源移除成功");
//    }

}
