package com.bingo.admin.dao;

import com.bingo.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
    User getUserByLoginName(String loginName);

    User getUserById(Long userId);

    @Query(value = "select count(1) from acc_user where login_name=?1",nativeQuery = true)
    int getCountByLoginName(String loginName);

}
