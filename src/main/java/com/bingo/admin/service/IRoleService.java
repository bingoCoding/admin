package com.bingo.admin.service;

import com.bingo.admin.entity.Role;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRoleService {

    Role save(Role role);

    List<Role> findAll();

    List<Map<String, Object>> findAll(long l);

    Role getRoleById(Long id);

    int checkRoleNameUnique(Long id, String roleName);

    Optional<Role> findOne(Specification<Role> specification);

    void deleteById(Long id);
}
