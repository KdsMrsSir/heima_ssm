package com.itheima.ssm.dao;/*
作用：  
 */

import com.itheima.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {


    @Select("select * from member where id = #{id}")
    public Member findById(String id);

}
