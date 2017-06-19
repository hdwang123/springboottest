package com.hdwang.dao.datajpa.secondDs;

import com.hdwang.entity.dbFirst.User;
import com.hdwang.entity.dbSecond.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by hdwang on 2017-06-17.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /**
     * spring data jpa 会自动注入实现（根据方法命名规范）
     * @return
     */
    User findByNumber(String number);


    @Modifying
    @Query("delete from Order o where o.id = :id")
    void deleteUser(@Param("id") int id);
}
