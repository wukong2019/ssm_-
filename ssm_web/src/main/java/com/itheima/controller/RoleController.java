package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true)Integer page,@RequestParam(value = "size",defaultValue = "5",required = true)Integer size){
        ModelAndView mv=new ModelAndView();
        List<Role> roles=roleService.findAll(page,size);
        PageInfo<Role> roleInfo=new PageInfo<>(roles);
        mv.addObject("roleList",roleInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv=new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }



    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //findRoleByIdAndAllPermission

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        List<Permission> permissions=roleService.findRoleByIdAndAllPermission(roleId);
        Role role = roleService.findById(roleId);
        ModelAndView mv=new ModelAndView();
        mv.addObject("permissionList",permissions);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return mv;


    }


    //addPermissionToRole

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissionId){

        roleService.addPermissionToRole(roleId,permissionId);
        return "redirect:findAll.do";
    }
}
