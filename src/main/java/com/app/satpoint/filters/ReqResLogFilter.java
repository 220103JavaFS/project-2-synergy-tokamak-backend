package com.app.satpoint.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class ReqResLogFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(ReqResLogFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ReqResLogFilter starting: {}", this);
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info("Request: {} : {}", req.getMethod(), req.getRequestURI());
        chain.doFilter(request, response);
        log.info("Response: {}", res.getContentType());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
