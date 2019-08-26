package com.itheima.ssm.dao;/*
作用：  
 */

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where  id in (select roleid from users_role where userid =#{userId} )")
    @Results({
         @Result(id=true,property = "id",column = "id"),
         @Result(property = "roleName",column = "roleName"),
         @Result(property = "roleDesc",column = "roleDesc"),
         @Result(property = "permissions",column = "id",javaType =java.util.List.class ,  many = @Many(
                 select = ("com.itheima.ssm.dao.IPermissionDao.findByRoleId"))),


    })
    public List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);



    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findUserByIdAndAllRole(String id);

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType =java.util.List.class ,  many = @Many(
                    select = ("com.itheima.ssm.dao.IPermissionDao.findPermissionsByRoleId"))),


    })
    Role findById(String id);

    @Insert("insert into role_permission values(#{pid},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("pid") String pid);
}
