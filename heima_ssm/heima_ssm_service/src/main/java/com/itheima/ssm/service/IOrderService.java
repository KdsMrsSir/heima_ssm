package com.itheima.ssm.service;/*
作用：  
 */

import com.itheima.ssm.domain.Orders;

import java.util.List;


public interface IOrderService {

    //List<Orders> findAll() throws Exception;
    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(String orderId);


}
