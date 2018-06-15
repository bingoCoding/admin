package com.bingo.admin.commons.security;

import com.bingo.admin.entity.Menu;
import com.bingo.admin.service.impl.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 接收用户请求的地址，返回访问该地址需要的所有权限
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    //private HashMap<String, Collection<ConfigAttribute>> map =null;
    LinkedHashMap<String, Collection<ConfigAttribute>> resourceMap=null;

    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine(){
        resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
//        List<Menu> resourceList = resourceService.getUrlResourceWithAuthorities();
//        for (Menu resource : resourceList) {
//            // TODO 处理权限菜单末节点绑定唯一资源的问题
//            String key = resource.getResString();
//            resourceMap.put(key, resource.getAuthNameCollection());
//        }

        // 得到所有权限自身拥有的权限,将权限自身的资源放置到映射表中
        List<Menu> menus = menuService.findAll();

        if (menus != null && menus.size() > 0) {
            for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext();) {
                Menu menu = (Menu) iterator.next();
                if (menu != null) {
                    String key = menu.getPerms();
                    if (!resourceMap.containsKey(key)) {
//                        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//                        for (Role a : menu.getAuthorities()) {
//                            ConfigAttribute ca = new SecurityConfig(a.getRoleName());
//                            atts.add(ca);
//                        }
                        resourceMap.put(key,SecurityConfig.createListFromCommaDelimitedString(menu.getRoleNames()));
                    } else {
                        Collection<ConfigAttribute> c = resourceMap.get(key);
                        ConfigAttribute ca = new SecurityConfig(menu.getMenuName());
                        if (!c.contains(ca)) {
                            c.add(ca);
                        }
                    }
                }
            }
        }

    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
    // 如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(resourceMap ==null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = resourceMap.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return resourceMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}