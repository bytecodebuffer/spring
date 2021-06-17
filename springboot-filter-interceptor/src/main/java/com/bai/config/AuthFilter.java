package com.bai.config;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("auth init 方法执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("auth doFilter 执行");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("auth destroy 执行");
    }
}
