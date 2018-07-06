package com.bingo.admin.service.impl;

import com.bingo.admin.dao.MenuDao;
import com.bingo.admin.dao.RoleDao;
import com.bingo.admin.dao.UserDao;
import com.bingo.admin.entity.Menu;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.IMenuService;
import com.bingo.admin.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MenuService implements IMenuService {

    @Resource
    private MenuDao menuDao;
    @Resource
    private RoleDao roleDao;

    @Override
    public Menu save(Menu menu) {
        return menuDao.save(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> roleMenuTreeData(Long roleId,Boolean perms){
        if (perms==null){
            perms=true;
        }
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();

        if (roleId==null){
            roleId=0L;
        }
        List<Map<String,Object>> menuList = menuDao.roleMenuTreeData(roleId);
        for (Map<String,Object> menu : menuList){
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.get("id"));
            deptMap.put("pId", menu.get("parent_id"));

            StringBuffer sb = new StringBuffer();
            sb.append(menu.get("menu_name"));
            if (perms){
                sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.get("perms") + "</font>");
            }
            deptMap.put("name", sb.toString());
            deptMap.put("checked", menu.get("icheck"));

            trees.add(deptMap);
        }
        return trees;
    }

    @Override
    public Menu getMenu(Long id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public Optional<Menu> findOne(Specification specification) {
        return menuDao.findOne(specification);
    }

    @Override
    public void deleteById(Long id) {
        menuDao.deleteById(id);
    }
}
