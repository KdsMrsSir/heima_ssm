package com.itheima.ssm.dao;/*
作用：  
 */

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findByRoleId(String id);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url)values(#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id} )")
    List<Permission> findPermissionsByRoleId(String id);

    @Delete("delete from permission where id = #{id}")
    void deletePermission(String id);
}
