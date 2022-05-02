package com.yfyy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @Author: zhang lei
 * @Date:2022/5/1 12:11
 */
public class BeforeLoginFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(BeforeLoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("This is a filter before UsernamePasswordAuthenticationFilter.");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
