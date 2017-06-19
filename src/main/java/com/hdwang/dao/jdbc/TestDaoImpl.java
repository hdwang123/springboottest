package com.hdwang.dao.jdbc;

import com.hdwang.entity.dbFirst.User;
import com.hdwang.entity.dbSecond.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Random;

/**
 * Created by hdwang on 2017/6/16.
 * 使用jdbcTemplate操作数据库
 */
@Repository
public class TestDaoImpl implements TestDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Resource(name="jdbcTemplate2")
    private JdbcTemplate jdbcTemplate2;

    @Resource(name="transactionTemplate2")
    private TransactionTemplate transactionTemplate2;

    /**
     * 手动控制事物测试
     * @param throwEx
     */
    @Override
    public void testTransactionManually(boolean throwEx) {

        try {
            transactionTemplate.execute(new TransactionCallback<Boolean>() {

                /**
                 * 事物代码
                 *
                 * @param transactionStatus 事物状态
                 * @return 是否成功
                 */
                @Override
                public Boolean doInTransaction(TransactionStatus transactionStatus) {
                    User user = new User();
                    user.setId(1);
                    int a = new Random().nextInt(10); //0-9
                    user.setNumber("10000u" + a);
                    jdbcTemplate.update("UPDATE USER SET NUMBER=? WHERE ID=?", new Object[]{user.getNumber(), user.getId()}, new int[]{Types.VARCHAR, Types.INTEGER});
                    if (throwEx) {
                        throw new RuntimeException("try throw exception"); //看看会不会回滚
                    }
                    return true;
                }
            });
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * 手动执行jdbc测试
     */
    @Override
    public void testJdbcTemplate() {
        User  user = new User();
        int a = new Random().nextInt(10); //0-9
        user.setNumber("10000i"+ a );
        user.setName("name"+a);
        this.jdbcTemplate.update("INSERT into USER(NUMBER,NAME )VALUES (?,?)",user.getNumber(),user.getName());
    }

    /**
     * 手动控制事物测试
     * @param throwEx
     */
    @Override
    public void testTransactionManually2(boolean throwEx) {
        try {
            transactionTemplate2.execute(new TransactionCallback<Boolean>() {

                /**
                 * 事物代码
                 *
                 * @param transactionStatus 事物状态
                 * @return 是否成功
                 */
                @Override
                public Boolean doInTransaction(TransactionStatus transactionStatus) {
                    Order order = new Order();
                    order.setId(1);
                    int a = new Random().nextInt(10); //0-9
                    order.setNumber("10000u" + a);
                    jdbcTemplate2.update("UPDATE `ORDERS` SET NUMBER=? WHERE ID=?", new Object[]{order.getNumber(), order.getId()}, new int[]{Types.VARCHAR, Types.INTEGER});
                    if (throwEx) {
                        throw new RuntimeException("try throw exception"); //看看会不会回滚
                    }
                    return true;
                }
            });
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * 手动执行jdbc测试
     */
    @Override
    public void testJdbcTemplate2() {
        Order order = new Order();
        int a = new Random().nextInt(10); //0-9
        order.setNumber("10000i"+ a );
        order.setPrice(a);
        this.jdbcTemplate2.update("INSERT into `ORDERS`(NUMBER,PRICE) VALUES (?,?)",new Object[]{order.getNumber(),order.getPrice()},new int[]{Types.VARCHAR,Types.DECIMAL});
    }
}
