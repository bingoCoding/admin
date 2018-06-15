package com.bingo.admin.service.impl;

import com.bingo.admin.dao.DomainDao;
import com.bingo.admin.entity.Domain;
import com.bingo.admin.service.IDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DomainService implements IDomainService {

    @Resource
    private DomainDao domainDao;

    @Override
    public Domain save(Domain domain) {
        return domainDao.save(domain);
    }
}
