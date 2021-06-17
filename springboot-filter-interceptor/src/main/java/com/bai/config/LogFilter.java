package com.bai.config;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

/**
 * 自定义过滤器
 */
@Slf4j
public class LogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log init 方法");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log doFilter 方法");
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        log.info("log  销毁 destroy 方法");
    }
}
