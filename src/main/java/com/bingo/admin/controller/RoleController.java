package com.bingo.admin.controller;

import com.bingo.admin.service.IRoleService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class RoleController {


    @Resource
    private IRoleService roleService;


}
