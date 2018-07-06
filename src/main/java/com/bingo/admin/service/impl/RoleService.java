package com.bingo.admin.service.impl;

import com.bingo.admin.dao.RoleDao;
import com.bingo.admin.entity.Role;
import com.bingo.admin.service.IRoleService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Map<String, Object>> findAll(long l) {
        return roleDao.findAll(l);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public int checkRoleNameUnique(Long id, String roleName) {
        return roleDao.checkRoleNameUnique(id,roleName);
    }

    @Override
    public Optional<Role> findOne(Specification<Role> specification) {
        return roleDao.findOne(specification);
    }

    @Override
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}
