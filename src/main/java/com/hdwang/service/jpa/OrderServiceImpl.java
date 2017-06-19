package com.hdwang.service.jpa;

import com.hdwang.dao.datajpa.secondDs.OrderRepository;
import com.hdwang.dao.jpa.OrderDao;
import com.hdwang.entity.dbSecond.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by hdwang on 2017-06-16.
 */
@Service("orderService1")
@Transactional("secondTransactionManager")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order getById(int id) {
        return orderDao.getById(id);
    }

    @Override
    public int addOrder(Order order,boolean throwEx) {
        int id = orderDao.addOrder(order);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return id;
    }
}
