package com.hdwang.service.jpa;

import com.hdwang.dao.jpa.UserDao;
import com.hdwang.entity.dbFirst.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hdwang on 2017/6/15.
 */
@Service("userService1")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByNumber(String number) {
        return userDao.getByNumber(number);
    }

    @Override
    public int addUser(User user,boolean throwEx) {
        int id= this.userDao.addUser(user);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return id;
    }

    @Override
    public void deleteUserById(int id) {
        this.userDao.deleteUserById(id);
    }

    @Override
    public User updateUser(User user) {
        return this.userDao.updateUser(user);
    }


}
