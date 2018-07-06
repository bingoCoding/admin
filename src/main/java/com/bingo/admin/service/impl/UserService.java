package com.bingo.admin.service.impl;

import com.bingo.admin.dao.DomainDao;
import com.bingo.admin.dao.RoleDao;
import com.bingo.admin.dao.UserDao;
import com.bingo.admin.entity.Domain;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.IUserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private DomainDao domainDao;

    @Override
    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public int checkUserNameUnique(String loginName) {
        return userDao.getCountByLoginName(loginName);
    }

    @Override
    public User saveUser(User user) {
        Domain d=domainDao.getDomainById(user.getDeptId());
        user.setParentId(d.getParentId());
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword().trim()));
        return userDao.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public List<User> findAll(Specification<User> specification) {
        return userDao.findAll(specification);
    }
}
