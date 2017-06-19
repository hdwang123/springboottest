package com.hdwang.dao.jpa;

import com.hdwang.entity.dbSecond.Order;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by hdwang on 2017-06-16.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    //@Resource(name="entityManager2")
    @PersistenceContext(unitName = "secondDs")
    private EntityManager entityManager;

    @Override
    public Order getById(int id) {
        return this.entityManager.find(Order.class,id);
    }

    @Override
    public int addOrder(Order order) {
        this.entityManager.persist(order);
        return order.getId();
    }
}
