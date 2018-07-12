package com.bingo.admin.controller;

import com.bingo.admin.commons.jpa.FilterBuilder;
import com.bingo.admin.commons.result.R;
import com.bingo.admin.entity.Role;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.IRoleService;
import com.bingo.admin.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;


    @ModelAttribute
    public Model getUser(@RequestParam(name = "id",required = false) Long id,Model model){
        if(id!=null){
            User user=userService.getUserById(id);
            user.setUseRoles(new LinkedList<Role>());
            model.addAttribute("user",user);
        }
        return  model;
    }

    @PreAuthorize("hasAuthority('sys:user')")
    @RequestMapping("/sys/user")
    public String toUser(HttpServletResponse response){
        return "/system/user/user";
    }

    @PreAuthorize("hasAuthority('sys:user:add')")
    @RequestMapping("/sys/user/add")
    public String toAdd(Model model){
        model.addAttribute("roles", roleService.findAll());
        return "/system/user/add";
    }

    @PreAuthorize("hasAuthority('sys:user:update')")
    @RequestMapping("/sys/user/edit/{id}")
    public String toEdit(@PathVariable Long id,Model model){
        model.addAttribute("user",userService.getUserById(id));
        model.addAttribute("roles", roleService.findAll(id));
        return "/system/user/edit";
    }

    @PreAuthorize("hasAuthority('sys:user')")
    @RequestMapping("/sys/user/list")
    @ResponseBody
    public PageInfo list(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo(userService.findAll());
    }

    /**
     * 校验用户名
     */
    @PostMapping("/sys/user/checkUserNameUnique")
    @ResponseBody
    public boolean checkUserNameUnique(String name)
    {
        int count=0;
        if (name != null&&name.trim().length()>0){
            count = userService.checkUserNameUnique(name);
        }
        return count==0?true:false;
    }

    /**
     * 保存用户
     */
    @PreAuthorize("hasAnyAuthority('sys:user:add','sys:user:update')")
    @PostMapping("/sys/user/save")
    @ResponseBody
    public R save(@ModelAttribute("user") User user,String[] roleIds ){
        user.setUserRolesByArray(roleIds);
        user=userService.saveUser(user);
        if (user.getId()!=null){
            return R.ok();
        }
        return R.error();
    }

    @PreAuthorize("hasAuthority('sys:user:remove')")
    @RequestMapping("/sys/user/remove/{id}")
    @ResponseBody
    public R remove(@PathVariable("id") Long id)
    {
        try {
            userService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok("删除成功");
    }

    @GetMapping("/testJdbc")
    @ResponseBody
    public List<Map<String,Object>> testJdbc(){
        logger.info("testJdbc testJdbc testJdbc testJdbc testJdbc testJdbc");
        return userService.testJdbc();
    }
}
