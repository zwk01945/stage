package com.example.common.util.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**************************************************************
 ***       S  T  A  G  E    多模块依赖项目                    ***
 **************************************************************
 *                                                            *
 *         Project Name : base                                *
 *                                                            *
 *         File Name : SpringBeanUtils.java                   *
 *                                                            *
 *         Programmer : Mr.zhang                              *
 *                                                            *
 *         Start Date : 2020/7/28 16:30                       *
 *                                                            *
 *         Last Update : 2020/7/28 16:30                      *
 *                                                            *
 *------------------------------------------------------------*
 * Functions:                                                 *
 *   获取到上下文以及bean的名字                                   *
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  */
@Component
public class SpringBeanUtils implements BeanNameAware, ApplicationContextAware {

    private  static ApplicationContext context;

    @Override
    public void setBeanName(String s) {
        System.out.println(s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanUtils.context == null) {
            SpringBeanUtils.context = applicationContext;
        }
    }

    public static Object getBean(String name) {
        return getContext().getBean(name);
    }

    public static Object getBean(Class<?> clazz) {
        return getContext().getBean(clazz);
    }

    public static Object getBean(String name,Class<?> clazz){
        return getContext().getBean(name,clazz);
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
