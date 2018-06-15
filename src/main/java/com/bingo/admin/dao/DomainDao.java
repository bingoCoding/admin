package com.bingo.admin.dao;

import com.bingo.admin.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainDao extends JpaRepository<Domain,Long> {
}
