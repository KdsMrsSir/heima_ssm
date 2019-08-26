package com.itheima.ssm.dao;/*
作用：  
 */

import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class,many = @Many(select = ("com.itheima.ssm.dao.IRoleDao.findByUserId")))

    })
    public UserInfo findByUsername(String username);

    @Select("SELECT * from users ")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class,many = @Many(select = ("com.itheima.ssm.dao.IRoleDao.findByUserId")))
    })
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class,many = @Many(select = ("com.itheima.ssm.dao.IRoleDao.findByUserId")))
    })
    UserInfo findById(String id);


    @Select("select * from users where id = #{id}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id",javaType = java.util.List.class,many = @Many(select = ("com.itheima.ssm.dao.IRoleDao.findUserByIdAndAllRole")))
    })
    UserInfo findUserByIdAndAllRole(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void saveRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId);
}
