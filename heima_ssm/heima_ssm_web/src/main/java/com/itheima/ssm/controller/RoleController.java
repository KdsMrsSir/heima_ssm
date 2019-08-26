package com.itheima.ssm.controller;/*
作用：  
 */

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<Role> roleList = roleService.findAll();

        ModelAndView mv = new ModelAndView();

        mv.addObject("roleList", roleList);

        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) {
         roleService.save(role);
        return "redirect:findAll.do";


    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id" ,required = true) String roleId) {
        Role role= roleService.findById(roleId);

        List<Permission> permissionsList = role.getPermissions();

        ModelAndView mv = new ModelAndView();
        mv.addObject("role", role);

        mv.addObject("permissionsList", permissionsList);

        mv.setViewName("role-permission-add");
        return mv;


    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId" ,required = true) String roleId,@RequestParam(name = "ids" ,required = true) String[] permissionIds) {

        roleService.addPermissionToRole(roleId,permissionIds);

        return "redirect:findAll.do";

    }


}
