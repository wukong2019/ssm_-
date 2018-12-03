package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;


//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll(){
//        ModelAndView mv=new ModelAndView();
//        List<Orders> orders = ordersService.findAll();
//        mv.addObject("ordersList",orders);
//        mv.setViewName("orders-list");
//        return mv;
//    }

    @RolesAllowed("ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true)Integer page,@RequestParam(name = "size",defaultValue = "5",required = true)Integer size){
        ModelAndView mv=new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,size);
        PageInfo<Orders> pageInfo=new PageInfo<>(orders);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }


    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        Orders orders = ordersService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;

    }

}
