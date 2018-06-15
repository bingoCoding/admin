package com.bingo.admin.dao;

import com.bingo.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<Menu,Long> {
}
