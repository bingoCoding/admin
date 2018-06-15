package com.bingo.admin.controller;

import com.bingo.admin.service.IDomainService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class DomainController {


    @Resource
    private IDomainService domainService;


}
