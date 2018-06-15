package com.bingo.admin.service;


import com.bingo.admin.entity.Menu;

import java.util.List;

public interface IMenuService {

    Menu save(Menu menu);

    List<Menu> findAll();
}
