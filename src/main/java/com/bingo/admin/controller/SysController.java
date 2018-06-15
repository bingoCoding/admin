package com.bingo.admin.controller;

import com.bingo.admin.entity.Menu;
import com.bingo.admin.utils.SpringSecurityUtils;
import com.bingo.admin.utils.TreeUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


@Controller
public class SysController {

    @RequestMapping("/index")
    public String login(HttpServletRequest request){
        Set<Menu> menus= SpringSecurityUtils.getCurrentUserMenus();
        List<Menu> list=new ArrayList<>(menus);
        Collections.sort(list, new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getOrderNum().compareTo(o2.getOrderNum());
            }
        });
        System.out.println("list:"+list);
        System.out.println("menus:"+ TreeUtils.getChildPerms(list,0));

        request.getSession().setAttribute("menus", TreeUtils.getChildPerms(list,0));
        request.getSession().setAttribute("user", SpringSecurityUtils.getCurrentUser());
        System.out.println(
                SpringSecurityUtils.getCurrentUser().toString()
        );
        return "index";
    }
}
