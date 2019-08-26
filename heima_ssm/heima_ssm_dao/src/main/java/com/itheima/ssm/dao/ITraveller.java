package com.itheima.ssm.dao;/*
作用：  
 */

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITraveller {


    @Select("select * from Traveller where  id = (" +
            "select travellerid from order_traveller where orderid =#{orderId} )")
    public List<Traveller> findByOrdersId(String orderId) throws Exception;
}
