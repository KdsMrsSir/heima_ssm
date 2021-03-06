package com.itheima.ssm.service;/*
作用：  
 */

import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll()throws Exception;

    void save(UserInfo userInfo)throws Exception;

    UserInfo findById(String id);

    UserInfo findUserByIdAndAllRole(String id);

    void saveRoleToUser(String userId, String[] roleId);
}
