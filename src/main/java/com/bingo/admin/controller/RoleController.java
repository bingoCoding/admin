package com.bingo.admin.controller;

import com.bingo.admin.commons.jpa.FilterBuilder;
import com.bingo.admin.commons.result.R;
import com.bingo.admin.entity.Role;
import com.bingo.admin.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;

@Controller
public class RoleController {


    @Resource
    private IRoleService roleService;

    @ModelAttribute
    public Model getUser(@RequestParam(name = "id",required = false) Long id,Model model){
        if(id!=null){
            Role role=roleService.getRoleById(id);
            role.setMenus(new LinkedHashSet<>());
            model.addAttribute("role",role);
        }
        return  model;
    }

    @RequestMapping("/sys/role")
    public String toUser(HttpServletResponse response){
        return "/system/role/role";
    }

    @RequestMapping("/sys/role/add")
    public String toAdd(Model model){
        model.addAttribute("roles", roleService.findAll(0L));
        return "/system/role/add";
    }
    @RequestMapping("/sys/role/edit/{id}")
    public String toEdit(@PathVariable Long id, Model model){
        model.addAttribute("role",roleService.getRoleById(id));
        return "/system/role/edit";
    }

    @RequestMapping("/sys/role/list")
    @ResponseBody
    public PageInfo list(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo(roleService.findAll());
    }

    /**
     * 校验角色名
     */
    @PostMapping("/sys/role/checkRoleNameUnique")
    @ResponseBody
    public boolean checkRoleNameUnique(String roleName,Long roleId)
    {
        Specification<Role> specification= new FilterBuilder().add("id","!=",roleId)
                .add("roleName","=",roleName).buildFilter();
        Optional<Role> optional= roleService.findOne(specification);
        return !optional.isPresent();
    }

    /**
     * 保存角色
     */

    @PostMapping("/sys/role/save")
    @ResponseBody
    public R save(@ModelAttribute("role") Role role,String[]menuIds){
        role.setMenusByArray(menuIds);
        role=roleService.save(role);
        if (role.getId()!=null){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/sys/role/remove/{id}")
    @ResponseBody
    public R remove(@PathVariable("id") Long id)
    {
        try {
            roleService.deleteById(id);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return R.error("删除失败\n该数据被其他数据引用");
        }catch (Exception e){
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok("删除成功");
    }

}
