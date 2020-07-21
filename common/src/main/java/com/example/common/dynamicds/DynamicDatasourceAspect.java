package com.example.common.dynamicds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ClassName: DynamicDatasourceAspect
 * Description:
 * date: 2020/7/21 13:13
 *
 * @author zwk
 */
@Aspect
@Component
@Order(-10)
public class DynamicDatasourceAspect {
    public static Logger logger = LoggerFactory.getLogger(DynamicDatasourceAspect.class);

    @Before("@within(com.example.common.dynamicds.DS) || @annotation(com.example.common.dynamicds.DS)")
    public void beforeSwitchDS(JoinPoint point) {
        logger.info("进去aspect切面");
        //获取需要访问的类的Class
        Class<?> clazz = point.getTarget().getClass();
        //获取需要访问的方法名
        String methodName = point.getSignature().getName();
        //获取参数
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            if (clazz.isAnnotationPresent(DS.class)) {
                DS ds = clazz.getAnnotation(DS.class);
                dataSource = ds.value();
            }
            Method method = clazz.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(DS.class)) {
                DS ds = method.getAnnotation(DS.class);
                dataSource = ds.value();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DataSourceContextHolder.setDbType(dataSource);
    }

    @After("@within(com.example.common.dynamicds.DS) || @annotation(com.example.common.dynamicds.DS)")
    public void afterSwitchDS (JoinPoint point) {
        DataSourceContextHolder.clearDbType();
        logger.info("数据源移除成功");
    }
}
