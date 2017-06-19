package com.hdwang.dao.jdbc;

/**
 * Created by hdwang on 2017/6/16.
 * 手动操作数据库
 */
public interface TestDao {

    void testTransactionManually(boolean throwEx);

    void testJdbcTemplate();

    void testTransactionManually2(boolean throwEx);

    void testJdbcTemplate2();
}
