package com.bingo.admin.dao;

import com.bingo.admin.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DomainDao extends JpaRepository<Domain,Long>, JpaSpecificationExecutor<Domain> {
    Domain getDomainById(Long id);

    @Query(value = "select count(1) from acc_domain WHERE id <> ?1 and dept_name = ?2",nativeQuery = true)
    int checkDeptNameUnique(Long deptId, String deptName);


}
