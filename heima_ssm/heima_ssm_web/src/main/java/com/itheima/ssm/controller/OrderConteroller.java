package com.itheima.ssm.controller;/*
作用：  
 */

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderConteroller {

    @Autowired
    private IOrderService orderService;

    /*未分页*/
   /* @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<Orders> ordersList = orderService.findAll();
        System.out.println("..............."+ordersList.size());
        ModelAndView mv = new ModelAndView();
        mv.addObject("ordersList", ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name="page",required = true, defaultValue = "1") Integer page,@RequestParam(name="size",required = true,defaultValue = "5") Integer size) throws Exception {


        List<Orders> ordersList = orderService.findAll(page,size);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findAll(@RequestParam(name="id",required = true) String orderId) throws Exception {


        Orders orders = orderService.findById(orderId);

        System.out.println(orders.getTravellers().get(0).getPhoneNum());



        ModelAndView mv = new ModelAndView();

        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }


}
