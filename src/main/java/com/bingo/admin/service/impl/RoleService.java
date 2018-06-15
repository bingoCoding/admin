package com.bingo.admin.service.impl;

import com.bingo.admin.dao.RoleDao;
import com.bingo.admin.entity.Role;
import com.bingo.admin.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService implements IRoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }
}
