package com.hdwang.controller.datajpa;

import com.alibaba.fastjson.JSONObject;
import com.hdwang.entity.dbSecond.Order;
import com.hdwang.service.datajpa.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hdwang on 2017/6/15.
 */
@Controller("order2")
@RequestMapping("/datajpa/order")
public class OrderController {
    /**
     * 日志（slf4j->logback）
     */
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 返回text格式数据
     * @param id 主键id
     * @return 用户json字符串
     */
    @RequestMapping("/get/id/{id}")
    @ResponseBody
    public String getOrderById(@PathVariable("id")String id){
        logger.info("request /order/get/id/{id}, parameter is "+id);
        Order order = orderService.findById(Integer.parseInt(id));
        return JSONObject.toJSONString(order);
    }


    @RequestMapping("/update/{id}/{number}/{price}")
    @ResponseBody
    public Order addUser(@PathVariable("id")int id, @PathVariable("number")String number, @PathVariable("price")double price,boolean throwEx){
        Order order = new Order();
        order.setId(id);
        order.setNumber(number);
        order.setPrice(price);
        Order orderNew = null;
        try{
            this.orderService.updateOrder(order, throwEx);
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return orderNew;
    }
}
