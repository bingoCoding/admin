package com.bingo.admin.controller;

import com.bingo.admin.service.IMenuService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class MenuController {


    @Resource
    private IMenuService menuService;


}
