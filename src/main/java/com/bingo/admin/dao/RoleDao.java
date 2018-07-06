package com.bingo.admin.dao;

import com.bingo.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface RoleDao extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role> {
    Role getRoleById(Long id);

    @Query(value = "select count(1) from acc_role where id <> ?1 and role_name=?2",nativeQuery = true)
    int checkRoleNameUnique(Long id, String roleName);

    @Query(value = "SELECT r.role_name,r.id,(CASE WHEN ur.user_id IS NOT NULL THEN TRUE ELSE FALSE END) as icheck FROM bin_admin.acc_role r \n" +
            "LEFT JOIN bin_admin.acc_user_role ur ON r.id=ur.role_id AND ur.user_id=?1",nativeQuery = true)
    List<Map<String,Object>> findAll(long l);
}
