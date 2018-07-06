package com.bingo.admin.commons.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import java.io.IOException;


/**
     *
     * 实现HTTP资源安全处理的过滤器接口
     *
     * <p>
     * 改造实现 securityMetadataSource url-授权关系动态管理
     * {@link FilterInvocationSecurityMetadataSource}.
     * <p>
     *
     * @author bingo
     */
    @Configuration
    public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

        private static Logger logger = LoggerFactory.getLogger(MyFilterSecurityInterceptor.class);

        // ~ Static fields/initializers
        // =====================================================================================

        private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";

        // ~ Instance fields
        // ================================================================================================

        private boolean observeOncePerRequest = true;

        // ~ Methods
        // ========================================================================================================

        @Autowired
        private FilterInvocationSecurityMetadataSource securityMetadataSource;

        @Autowired
        public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
            super.setAccessDecisionManager(myAccessDecisionManager);
        }

    /**
         * Not used (we rely on IoC container lifecycle services instead)
         *
         * @param arg0
         *            ignored
         *
         * @throws ServletException
         *             never thrown
         */
        public void init(FilterConfig arg0) throws ServletException {
        }

        /**
         * Not used (we rely on IoC container lifecycle services instead)
         */
        public void destroy() {
        }

        /**
         * Method that is actually called by the filter chain. Simply delegates to
         * the {@link #invoke(FilterInvocation)} method.
         *
         * @param request
         *            the servlet request
         * @param response
         *            the servlet response
         * @param chain
         *            the filter chain
         *
         * @throws IOException
         *             if the filter chain fails
         * @throws ServletException
         *             if the filter chain fails
         */
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                ServletException {
            FilterInvocation fi = new FilterInvocation(request, response, chain);
            invoke(fi);
        }

        public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
            return this.securityMetadataSource;
        }

        @Override
        public Class<? extends Object> getSecureObjectClass() {
            return FilterInvocation.class;
        }

        public void invoke(FilterInvocation fi) throws IOException, ServletException {
//            if ((fi.getRequest() != null) && (fi.getRequest().getAttribute(FILTER_APPLIED) != null)
//                    && observeOncePerRequest) {
//                // filter already applied to this request and user wants us to
//                // observe
//                // once-per-request handling, so don't re-do security checking
//                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
//            } else {
                // first time this request being called, so perform security
                // checking
//                if (fi.getRequest() != null) {
//                    fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
//                }

                InterceptorStatusToken token = super.beforeInvocation(fi);

                try {
                    fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                } finally {
                    super.afterInvocation(token, null);
                }
//            }
        }

        /**
         * Indicates whether once-per-request handling will be observed. By default
         * this is <code>true</code>, meaning the
         * <code>FilterSecurityInterceptor</code> will only execute
         * once-per-request. Sometimes users may wish it to execute more than once
         * per request, such as when JSP forwards are being used and filter security
         * is desired on each included fragment of the HTTP request.
         *
         * @return <code>true</code> (the default) if once-per-request is honoured,
         *         otherwise <code>false</code> if
         *         <code>FilterSecurityInterceptor</code> will enforce
         *         authorizations for each and every fragment of the HTTP request.
         */
        public boolean isObserveOncePerRequest() {
            return observeOncePerRequest;
        }


        public SecurityMetadataSource obtainSecurityMetadataSource() {
//            if (securityMetadataSource == null) {
//                LinkedHashMap<RequestKey, Collection<ConfigAttribute>> requestMap;
//                try {
//                    requestMap = buildRequestMap();
//                    securityMetadataSource = new DefaultFilterInvocationSecurityMetadataSource(requestMap);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            return securityMetadataSource;
        }


        public void setObserveOncePerRequest(boolean observeOncePerRequest) {
            this.observeOncePerRequest = observeOncePerRequest;
        }

        // --------------------

        /**
         * 提供Ant Style的URLMatcher.
         */
        //protected AntPathMatcher getUrlMatcher() {
//            return new AntPathMatcher();
//        }

        /**
         * 将resourceDetailService提供LinkedHashMap<String, String>形式的URL及授权关系定义
         * 转化为DefaultFilterInvocationDefinitionSource需要的LinkedHashMap<RequestKey,
         * ConfigAttributeDefinition>形式.
         */
//        protected LinkedHashMap<RequestKey, Collection<ConfigAttribute>> buildRequestMap() throws Exception {
//            LinkedHashMap<RequestKey, Collection<ConfigAttribute>> resourceMap = new LinkedHashMap<RequestKey, Collection<ConfigAttribute>>();
//            List<Resource> resourceList = resourceService.getUrlResourceWithAuthorities();
//            for (Resource resource : resourceList) {
//                RequestKey key = new RequestKey(resource.getResString(), null);
//                resourceMap.put(key, resource.getAuthNameCollection());
//            }
//
//            // 得到所有权限自身拥有的权限,将权限自身的资源放置到映射表中
//            List<Authority> authoritys = authorityService.getCompleteAuthority();
//
//            if (authoritys != null && authoritys.size() > 0) {
//                for (Iterator<Authority> iterator = authoritys.iterator(); iterator.hasNext();) {
//                    Authority authority = (Authority) iterator.next();
//                    Resource res = authority.getResource();
//                    if (res != null) {
//                        RequestKey key = new RequestKey(res.getResString(), null);
//                        ConfigAttribute ca = new SecurityConfig(authority.getAuthority());
//                        if (!resourceMap.containsKey(key)) {
//                            resourceMap.put(key,
//                                    SecurityConfig.createListFromCommaDelimitedString(authority.getAuthority()));
//                        } else {
//                            Collection<ConfigAttribute> c = resourceMap.get(key);
//                            if (!c.contains(ca)) {
//                                c.add(ca);
//                            }
//                        }
//                    }
//                }
//            }
//            return resourceMap;
//        }
    }
