package com.bingo.admin.service.impl;

import com.bingo.admin.commons.jpa.FilterBuilder;
import com.bingo.admin.dao.DomainDao;
import com.bingo.admin.entity.Domain;
import com.bingo.admin.service.IDomainService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DomainService implements IDomainService {

    @Resource
    private DomainDao domainDao;

    @Override
    public Domain save(Domain domain) {
        return domainDao.save(domain);
    }

    @Override
    public Domain getDept(Long id) {
        return domainDao.getOne(id);
        //return domainDao.getDomainById(id);
    }

    @Override
    public List<Domain> treeData() {
        return domainDao.findAll();
    }

    @Override
    public List<Domain> findAll() {
        return domainDao.findAll();
    }

    @Override
    public Optional<Domain> findOne(Specification specification) {
        return domainDao.findOne(specification);
    }

    @Override
    public void deleteById(Long id) {
        domainDao.deleteById(id);
    }
}
