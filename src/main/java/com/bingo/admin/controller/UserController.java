package com.bingo.admin.controller;

import com.bingo.admin.entity.User;
import com.bingo.admin.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
public class UserController {


    @Resource
    private IUserService userService;

    @RequestMapping("/test/{username}")
    @ResponseBody
    public User getUser(@PathVariable("username") String username){
        return userService.getUserByUserName(username);
    }

    @RequestMapping("/save")
    @ResponseBody
    public User saveUser(){
        User user=new User();
        user.setUserName("admin");
        user.setPassword("123456");
        user.setSex("2");
        return userService.save(user);
    }

}
