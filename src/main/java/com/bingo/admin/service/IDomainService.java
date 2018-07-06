package com.bingo.admin.service;

import com.bingo.admin.entity.Domain;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface IDomainService {

    Domain save(Domain domain);

    Domain getDept(Long id);

    List<Domain> treeData();

    List<Domain> findAll();

    Optional<Domain> findOne(Specification specification);

    void deleteById(Long id);
}
