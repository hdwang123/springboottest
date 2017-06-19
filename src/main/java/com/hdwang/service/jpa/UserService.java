package com.hdwang.service.jpa;

import com.hdwang.entity.dbFirst.User;

/**
 * Created by hdwang on 2017/6/15.
 */
public interface UserService {

    User getById(int id);

    User getByNumber(String number);

    int addUser(User user,boolean throwEx);

    void deleteUserById(int id);

    User updateUser(User user);
}
