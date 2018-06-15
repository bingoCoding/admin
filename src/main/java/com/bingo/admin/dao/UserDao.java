package com.bingo.admin.dao;

import com.bingo.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User getUserByUserName(String username);

    User getUserById(Long userId);
}
