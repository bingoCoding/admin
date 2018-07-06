package com.bingo.admin.service;

import com.bingo.admin.entity.Menu;
import com.bingo.admin.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IMenuService {

    Menu save(Menu menu);

    List<Menu> findAll();

    List<Map<String,Object>> roleMenuTreeData(Long id, Boolean perms);

    Menu getMenu(Long id);

    Optional<Menu> findOne(Specification specification);

    void deleteById(Long id);
}
