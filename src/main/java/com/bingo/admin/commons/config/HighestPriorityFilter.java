package com.bingo.admin.commons.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bingo.admin.commons.exception.ThreadCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 最先执行的过滤器（系统自带的除外）
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HighestPriorityFilter implements Filter{

    /** 日志 */
    private static final Logger LOG = LoggerFactory.getLogger(HighestPriorityFilter.class);

    /**
     * 初始化
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("HighestPriorityFilter init");
    }

    /**
     * 过滤处理
     *
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        boolean flag=true;
        String[] exUrl=new String[]{"/static/","/css/","/js/","/ajax/","/img/","/itemjs/","/fonts/","/sys/"};
        for (String url : exUrl){
            if (httpRequest.getRequestURI().startsWith(url)){
                flag=false;
                break;
            }
        }
        if(flag){
            ThreadCache.setUri(httpRequest.getRequestURI());
        }
        System.out.println("ThreadCache.getUri()===="+ThreadCache.getUri());
        try {
            chain.doFilter(httpRequest, response);
        } finally {
            ThreadCache.removeUri();
        }
    }

    /**
     * 资源销毁
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        LOG.info("HighestPriorityFilter destroy");
    }
}
