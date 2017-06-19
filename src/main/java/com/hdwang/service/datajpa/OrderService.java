package com.hdwang.service.datajpa;

import com.hdwang.entity.dbSecond.Order;

/**
 * Created by hdwang on 2017-06-17.
 */
public interface OrderService {

    Order findById(int id);

    Order updateOrder(Order order, boolean throwEx);

}
