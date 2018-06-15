package com.bingo.admin.service.impl;

import com.bingo.admin.dao.MenuDao;
import com.bingo.admin.entity.Menu;
import com.bingo.admin.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService implements IMenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public Menu save(Menu menu) {
        return menuDao.save(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }
}
