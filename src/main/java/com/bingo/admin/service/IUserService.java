package com.bingo.admin.service;


import com.bingo.admin.entity.User;

public interface IUserService {

    User getUserByUserName(String username);

    User save(User user);

    User getUserById(Long userId);
}
