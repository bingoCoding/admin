package com.bingo.admin.service;

import com.bingo.admin.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IUserService {

    User getUserByLoginName(String LoginName);

    User save(User user);

    User getUserById(Long userId);

    List<User> findAll();

    int checkUserNameUnique(String loginName);

    User saveUser(User user);

    void deleteById(Long id);

    List<User>  findAll(Specification<User> specification);
}
