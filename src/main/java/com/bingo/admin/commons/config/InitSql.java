package com.bingo.admin.commons.config;

import com.bingo.admin.entity.Domain;
import com.bingo.admin.entity.Menu;
import com.bingo.admin.entity.Role;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.IDomainService;
import com.bingo.admin.service.IMenuService;
import com.bingo.admin.service.IRoleService;
import com.bingo.admin.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//@Component
public class InitSql implements CommandLineRunner {

    @Resource
    private IDomainService domainService;
    @Resource
    private IMenuService menuService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IUserService userService;

    @Override
    public void run(String... args) throws Exception {
        Domain domain=new Domain();
        domain.setPostCode("10000000000");
        domain.setPostName("超级管理部");
        domain.setPostSort("0");
        domain=domainService.save(domain);

        Menu menu=new Menu();
        menu.setMenuName("系统管理");
        menu.setMenuType("0");
        menu.setOrderNum("0");
        menu.setParentId(0L);
        menu.setPerms("sys");
        menu.setUrl("sys");
        menu=menuService.save(menu);

        Menu menu2=new Menu();
        menu2.setMenuName("用户管理");
        menu2.setMenuType("1");
        menu2.setOrderNum("1");
        menu2.setParentId(2L);
        menu2.setPerms("sys:user");
        menu2.setUrl("sys:user");
        menu2=menuService.save(menu2);

        Menu menu3=new Menu();
        menu3.setMenuName("角色管理");
        menu3.setMenuType("2");
        menu3.setOrderNum("2");
        menu3.setParentId(2L);
        menu3.setPerms("sys:role");
        menu3.setUrl("sys:role");
        menu3=menuService.save(menu3);

        Role role=new Role();
        Set<Menu> menus=new LinkedHashSet<>();
        menus.add(menu);
        menus.add(menu2);
        menus.add(menu3);
        role.setMenus(menus);
        role.setRoleKey("admin");
        role.setRoleName("超级管理员");
        role.setRoleSort("0");
        role=roleService.save(role);
        Set<Role> roles=new LinkedHashSet<>();
        roles.add(role);
        menu.setAuthorities(roles);
        menu=menuService.save(menu);
        menu2.setAuthorities(roles);
        menu2=menuService.save(menu2);
        menu3.setAuthorities(roles);
        menu3=menuService.save(menu3);

        User user=new User();
        List<Role> roleList=new LinkedList<>();
        roles.add(role);
        user.setUseRoles(roleList);
        user.setDeptId(domain.getId());
        user.setEmail("2668551511@qq.com");
        user.setLoginName("admin");
        user.setUserName("bingo");
        user.setPassword("$2a$10$UP8SyJQZ044N8s9L.QN36uMCkMaqJ/s8Mo352G0rQUHfz0smLUfQC");
        user.setParentId(0L);
        user.setUserType("Y");
        user.setSex("0");
        userService.save(user);
    }
}
