package com.itheima.ssm.service;/*
作用：  
 */

import com.itheima.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    //查询所有
    public List<Product> findAll() throws Exception;

    public void save(Product product);
}
