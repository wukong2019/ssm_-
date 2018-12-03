package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true)Integer page,@RequestParam(name = "size",defaultValue = "5",required = true)Integer size){
        List<UserInfo> users = userService.findAll(page,size);
        ModelAndView mv=new ModelAndView();
        PageInfo<UserInfo> userInfo=new PageInfo<>(users);
        mv.addObject("userList",userInfo);
        mv.setViewName("user-list");
        return mv ;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }


    //查询用户有哪些角色可以添加
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        //查询用户
        UserInfo userInfo = userService.findById(id);
        //根据用户id查询可以添加的角色
        List<Role> roles=userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] rolesId){
        userService.addRoleToUser(userId,rolesId);
        return "redirect:findAll.do";

    }







//    @RequestMapping("/findById.do")
//    public ModelAndView findById(String id,@RequestParam(name = "page",defaultValue = "1",required = true)int page,@RequestParam(name = "size",defaultValue = "5",required = true)int size){
//        ModelAndView mv=new ModelAndView();
//        UserInfo users=userService.findById(id,page,size);
//        PageInfo<Role> roleInfo=new PageInfo<>(users.getRoles());
//        mv.addObject("roles",roleInfo);
//        mv.setViewName("user-show");
//        return mv;
//    }
}
