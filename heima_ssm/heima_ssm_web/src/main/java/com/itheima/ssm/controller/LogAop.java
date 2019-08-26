package com.itheima.ssm.controller;/*
作用：  
 */

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date visitTime;
    private String username;
    private Long executionTime;
    private String ip;
    private String url;
    private Class clazz;
    private Method method;

    @Before("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//访问时间
        clazz = jp.getTarget().getClass();//访问的类
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();

        Class[] propertyClass = new Class[args.length] ;

        //获取method方法
        for (int i = 0;i<args.length;i++){
            propertyClass[i] = args[i].getClass();
        }
            if (args == null || args.length == 0) {
                method = clazz.getMethod(methodName);
            } else {
                method = clazz.getMethod(methodName, propertyClass);
            }
    }

    @After("execution(* com.itheima.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        executionTime = new Date().getTime() - visitTime.getTime();//获取访问时长



        if (clazz!=null&&method!=null&&clazz!= LogAop.class) {
            RequestMapping classAnnotation  = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }


            }



        }
        //获取ip地址
         ip = request.getRemoteAddr();
        //获取用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        username = user.getUsername();
        SysLog sysLog = new SysLog();

        sysLog.setVisitTime(visitTime);
        sysLog.setVisitTimeStr(visitTime.toString());
        sysLog.setIp(ip);
        sysLog.setUrl(url);
        sysLog.setExecutionTime(executionTime);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名 ]"+clazz.getName()+"[方法名 ]"+method.getName());

        sysLogService.save(sysLog);
    }





}
