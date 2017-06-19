package com.hdwang.dao.jpa;

import com.hdwang.entity.dbFirst.User;

/**
 * Created by hdwang on 2017/6/15.
 * jpa方式操作数据库
 */
public interface UserDao{

    User getById(int id);

    User getByNumber(String number);

    int addUser(User user);

    void deleteUserById(int id);

    User updateUser(User user);

}
