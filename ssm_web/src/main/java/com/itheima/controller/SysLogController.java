package com.itheima.controller;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequestMapping("/sysLog")
@Controller
@Transactional
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findByPage(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "10")Integer size){

        List<SysLog> sysLogList=sysLogService.findByPage(page,size);
        PageInfo<SysLog> pageInfo=new PageInfo<>(sysLogList);

        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;

    }





}
