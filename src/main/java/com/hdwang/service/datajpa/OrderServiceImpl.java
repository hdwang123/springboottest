package com.hdwang.service.datajpa;

import com.hdwang.dao.datajpa.firstDs.UserRepository;
import com.hdwang.dao.datajpa.secondDs.OrderRepository;
import com.hdwang.entity.dbFirst.User;
import com.hdwang.entity.dbSecond.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hdwang on 2017-06-17.
 */
@Service("orderService2")
@Transactional("secondTransactionManager")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Order findById(int id) {
        return this.orderRepository.findOne(id);
    }

    @Override
    public Order updateOrder(Order order, boolean throwEx) {
        Order orderNew = this.orderRepository.save(order);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return orderNew;
    }
}
