package com.bai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LogInterceptor()).addPathPatterns("/*").order(0);
        //registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/*").order(1);
    }
}
