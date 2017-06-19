package com.hdwang.dao.jpa;

import com.hdwang.entity.dbSecond.Order;

/**
 * Created by hdwang on 2017/6/15.
 * jpa方式操作数据库
 */
public interface OrderDao {

    Order getById(int id);

    int addOrder(Order order);

}
