package com.itheima.controller;

import com.itheima.controller.SysLogController;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
@Transactional
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date StartTime;// 访问时间
    private Class clazz;// 访问的类
    private Method method;// 访问的方法



    @Before("execution(* com.itheima.controller.*.*(..))")
    public void beforeDo(JoinPoint joinPoint) throws NoSuchMethodException {

        // 访问时间
        StartTime = new Date();

        // 获取访问的类
        clazz=joinPoint.getTarget().getClass();

        // 获取访问的方法
        String methodnName=joinPoint.getSignature().getName();

        if(clazz!=null&&methodnName!=null){

            Object[] args = joinPoint.getArgs();
            if(args.length>0&&args!=null){
                Class[] classes=new Class[args.length];

                for (int i = 0; i < args.length; i++) {
                    classes[i]=args[i].getClass();
                }
                 method = clazz.getMethod(methodnName, classes);
            }else {
                method=clazz.getMethod(methodnName);
            }
        }
    }


    @After("execution(* com.itheima.controller.*.*(..))")
    public void afterDo(JoinPoint joinPoint){
        Long visttime=new Date().getTime()- StartTime.getTime();//访问时长

        String url="";
        if(clazz!=SysLogController.class&&clazz!=null&&method!=null){
            // 获取类上的@RequestMapping对象
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            String[] classvalue = classAnnotation.value();

            if(classAnnotation!=null){
                // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(method!=null){
                    String[] methodvalue = methodAnnotation.value();
                    url=classvalue[0]+methodvalue[0];


                    //获取方法的全限定名
                    String methodStr ="类名" +clazz.getName() +"方法名" +method.getName();
                    // 当访问查看日志时，不需要记录该功能信息到 日志表 中
                    if("com.itheima.ssm.controller.SysLogController".equals(clazz.getName()) && "findByPage".equals(method.getName())){
                        return;
                    }
                    // 获取ip
                    StringBuffer requestURL = request.getRequestURL();
                    System.out.println(requestURL);
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    SysLog sysLog=new SysLog();
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setExecutionTime(visttime);
                    sysLog.setMethod(methodStr);
                    sysLog.setVisitTime(StartTime);
                    sysLogService.save(sysLog);
                }
            }
        }




    }


}
