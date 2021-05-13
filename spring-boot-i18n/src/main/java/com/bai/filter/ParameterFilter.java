package com.bai.filter;

import com.bai.util.ThreadLocalUtils;
import lombok.Data;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParameterFilter implements Filter {

    /**
     * 语言环境
     */
    private static final String HEADER_LOCALE = "lang";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Map<String, Object> extendParams = getLocal(httpServletRequest);
        for (Map.Entry<String, Object> map : extendParams.entrySet()) {
            request.setAttribute(map.getKey(), map.getValue());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 获取请求头中的公共参数信息
     *
     * @param request 请求
     * @return 公共参数
     */
    public Map<String, Object> getLocal(HttpServletRequest request) {
        String local = request.getParameter(HEADER_LOCALE);
        Map<String, Object> params = new HashMap<>(2);
        params.put("locale", local);
        ThreadLocalUtils.saveLocale(local);
        return params;
    }

}
