package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll();

    void save(Permission permission);

    void deletePermission(String id);
}
