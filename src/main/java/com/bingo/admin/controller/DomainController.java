package com.bingo.admin.controller;

import com.bingo.admin.commons.jpa.FilterBuilder;
import com.bingo.admin.commons.result.R;
import com.bingo.admin.entity.Domain;
import com.bingo.admin.entity.Role;
import com.bingo.admin.entity.User;
import com.bingo.admin.service.IDomainService;
import com.bingo.admin.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class DomainController {


    @Resource
    private IDomainService domainService;

    @ModelAttribute
    public Model getDomain(@RequestParam(name = "id",required = false) Long id, Model model){
        if(id!=null){
            Domain domain=domainService.getDept(id);
            model.addAttribute("dept",domain);
        }
        return  model;
    }

    @RequestMapping("/sys/dept")
    public String toUser(HttpServletResponse response){
        return "/system/dept/dept";
    }

    @RequestMapping("/sys/dept/add/{id}")
    public String toAdd(Model model,@PathVariable Long id){
        Domain dept = domainService.getDept(id);
        model.addAttribute("dept", dept);
        return "/system/dept/add";
    }

    @RequestMapping("/sys/dept/edit/{id}")
    public String toEdit(@PathVariable Long id, Model model){
        model.addAttribute("dept",domainService.getDept(id));
        return "/system/dept/edit";
    }

    @RequestMapping("/sys/dept/list")
    @ResponseBody
    public List<Domain> list(){
        return domainService.findAll();
    }

    /**
     * 校验名称
     */
    @PostMapping("/sys/dept/checkDeptNameUnique")
    @ResponseBody
    public boolean checkDeptNameUnique(String deptName,Long deptId)
    {
        Optional<Domain> optional=null;
        if (deptName != null&&deptName.trim().length()>0){
            optional=domainService.findOne(new FilterBuilder<Domain>().add("id","!=",deptId).add("deptName","=",deptName).buildFilter());
        }
        return !optional.isPresent();
    }

    /**
     * 保存
     */

    @PostMapping("/sys/dept/save")
    @ResponseBody
    public R save(@ModelAttribute("dept") Domain dept){
        dept=domainService.save(dept);
        if (dept.getId()!=null){
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/sys/dept/getDept/{id}")
    public String getDeptTree(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request){
        model.addAttribute("deptName",domainService.getDept(id).getDeptName());
        return "system/dept/tree";
    }

    @ResponseBody
    @RequestMapping("sys/dept/treeData")
    public List<Map<String,Object>> treeData(){
        List<Domain> list=domainService.treeData();
        List<Map<String,Object>> tree=new ArrayList<>();
        if(list!=null&&list.size()>0){
            list.stream().forEach((domain)->{
                Map<String,Object> map=new HashMap<>(3);
                map.put("id",domain.getId());
                map.put("pId",domain.getParentId());
                map.put("name",domain.getDeptName());
                tree.add(map);
            });
        }
        return tree;
    }

    @RequestMapping("/sys/dept/remove/{id}")
    @ResponseBody
    public R remove(@PathVariable("id") Long id)
    {
        try {
            domainService.deleteById(id);
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
