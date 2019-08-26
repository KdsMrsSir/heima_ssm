package com.itheima.ssm.service.impl;/*
作用：  
 */

import com.itheima.ssm.dao.IRoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        Role role= roleDao.findById(id);
        return role;
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {

        for (String pid:permissionIds
             ) {
            roleDao.addPermissionToRole(roleId,pid);
        }
    }

}
