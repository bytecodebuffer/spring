package com.bai.config;

import com.bai.filter.ParameterFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<ParameterFilter> commonParameterFilter(){
        FilterRegistrationBean<ParameterFilter> registrationBean = new FilterRegistrationBean<>();
        ParameterFilter filter = new ParameterFilter();
        registrationBean.setFilter(filter);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(2);
        return registrationBean;
    }


}
