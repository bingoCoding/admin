package com.bingo.admin.service.impl;

import com.bingo.admin.dao.UserDao;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }
}
