package com.bingo.admin.controller;

import com.bingo.admin.commons.jpa.FilterBuilder;
import com.bingo.admin.commons.result.R;
import com.bingo.admin.entity.Domain;
import com.bingo.admin.entity.Menu;
import com.bingo.admin.service.IDomainService;
import com.bingo.admin.service.IMenuService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MenuController {


    @Resource
    private IMenuService menuService;

    @ModelAttribute
    public Model getMenu(@RequestParam(name = "id",required = false) Long id, Model model){
        if(id!=null){
            Menu menu=menuService.getMenu(id);
            model.addAttribute("menu",menu);
        }
        return  model;
    }

    @RequestMapping("/sys/menu")
    public String toUser(HttpServletResponse response){
        return "/system/menu/menu";
    }

    @RequestMapping("/sys/menu/add/{id}")
    public String toAdd(Model model,@PathVariable Long id){
        if (0L == id){
            Menu menu = new Menu();
            menu.setId(0L);
            menu.setMenuName("主目录");
            model.addAttribute("menu", menu);
        } else{
            Menu menu = menuService.getMenu(id);
            model.addAttribute("menu", menu);
        }
        return "/system/menu/add";
    }

    @RequestMapping("/sys/menu/edit/{id}")
    public String toEdit(@PathVariable Long id, Model model){
        model.addAttribute("menu",menuService.getMenu(id));
        return "/system/menu/edit";
    }

    @RequestMapping("/sys/menu/list")
    @ResponseBody
    public List<Menu> list(){
        return menuService.findAll();
    }

    /**
     * 校验名称
     */
    @PostMapping("/sys/menu/checkMenuNameUnique")
    @ResponseBody
    public boolean checkDeptNameUnique(String menuName,Long menuId){
        Optional<Menu> optional=null;
        if (menuName != null&&menuName.trim().length()>0){
            optional=menuService.findOne(new FilterBuilder<Domain>().add("id","!=",menuId).add("menuName","=",menuName).buildFilter());
        }
        return !optional.isPresent();
    }

    /**
     * 保存
     */

    @PostMapping("/sys/menu/save")
    @ResponseBody
    public R save(@ModelAttribute("menu") Menu menu){
        menu=menuService.save(menu);
        if (menu.getId()!=null){
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/sys/menu/roleMenuTreeData")
    public List<Map<String, Object>> roleMenuTreeData(Long roleId){
        return menuService.roleMenuTreeData(roleId,true);
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/sys/menu/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData()
    {
        List<Map<String, Object>> tree = menuService.roleMenuTreeData(0L,false);
        return tree;
    }
    /**
     * 选择菜单树
     */
    @GetMapping("/sys/menu/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long  menuId, Model model)
    {
        model.addAttribute("treeName", menuService.getMenu(menuId).getMenuName());
        return "/system/menu/tree";
    }

    @RequestMapping("/sys/menu/remove/{id}")
    @ResponseBody
    public R remove(@PathVariable("id") Long id)
    {
        try {
            menuService.deleteById(id);
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
