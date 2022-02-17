package com.app.satpoint.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class SessionFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        if ("OPTIONS".equals(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request,response);
        } else {
        if(req.getSession(false)==null){
            log.info("Got Request with no session");

            List<String> noSessionRequired = new ArrayList<>();
            noSessionRequired.add("/");
            noSessionRequired.add("/logout");
            noSessionRequired.add("/user");
            noSessionRequired.add("/user/session");
            noSessionRequired.add("/user/sessionkill");



            //if request is for endpoints that don't need a session
            if(noSessionRequired.contains(req.getRequestURI())){
                chain.doFilter(request, response);
            }else {
                //unauthorized
                res.setStatus(401);
            }


        }else {
            log.info("Got Request with session");
            chain.doFilter(request,response);
        }

    }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
