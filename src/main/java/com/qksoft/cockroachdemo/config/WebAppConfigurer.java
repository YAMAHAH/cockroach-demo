package com.qksoft.cockroachdemo.config;

import com.qksoft.cockroachdemo.interceptor.PageParamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    PageParamInterceptor pageParamInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(pageParamInterceptor).addPathPatterns("/**");
    }
}
