package com.hdwang.service.jpa;

import com.hdwang.entity.dbSecond.Order;

/**
 * Created by hdwang on 2017/6/15.
 */
public interface OrderService {


    Order getById(int id);

    int addOrder(Order order,boolean throwEx);
}
