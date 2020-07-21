package com.example.common.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Description: mvc扩展
 * date: 2020/7/17 10:43
 * @author zwk
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 全局拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加字符拦截器
        registry.addInterceptor(new DefenseInterceptor()).addPathPatterns("/**");
    }


    /**
     * 全局cors跨域解决
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("Content-Type","accept","Origin","Access-Control-Request-Method","Access-Control-Request-Headers")
                .allowedMethods("GET","POST","PUT","OPTIONS")
                .exposedHeaders("Access-Control-Allow-Origin","Access-Control-Allow-Credentials")
                .maxAge(3600);
    }


}
