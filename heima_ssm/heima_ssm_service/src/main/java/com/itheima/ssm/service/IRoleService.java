package com.itheima.ssm.service;/*
作用：  
 */

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {


    public List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
