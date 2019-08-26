package com.itheima.ssm.controller;/*
作用：  
 */

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<Permission> permissionList = permissionService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");

        return mv;

    }

    @RequestMapping("/save.do")
    public String save(Permission permission) {
            permissionService.save(permission);
            return "redirect:findAll.do";


    }

    @RequestMapping("/findUserByIdAndAllPermission.do")
    public String findUserByIdAndAllPermission(Permission permission) {
            permissionService.save(permission);
            return "redirect:findAll.do";


    }

    @RequestMapping("/deletePermission.do")
    public String deletePermission(String id) {
            permissionService.deletePermission(id);
            return "redirect:findAll.do";


    }

}
