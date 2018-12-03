package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true)int page, @RequestParam(name = "size",defaultValue = "3",required = true)int size){
       List<Permission> permissions= permissionService.findAll(page,size);
       ModelAndView mv=new ModelAndView();
        PageInfo<Permission> permissionInfo=new PageInfo<>(permissions);
        mv.addObject("permissionList",permissionInfo);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
       Permission permission= permissionService.findById(id);
       ModelAndView mv=new ModelAndView();
       mv.addObject("permission",permission);
       mv.setViewName("permission-update");
       return mv;

    }

    /*
    * 跟新
    * */
    @RequestMapping("/updateByPermissionId.do")
    public String updateByPermissionId(Permission permission){
        permissionService.updateByPermissionId(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteByPermissionId.do")
    public String deleteByPermissionId(String id){
        permissionService.deleteByPermissionId(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteByIds.do")
    public String deleteByIds(String[] ids){
        permissionService.deleteByIds(ids);
        return "redirect:findAll.do";
    }


}
