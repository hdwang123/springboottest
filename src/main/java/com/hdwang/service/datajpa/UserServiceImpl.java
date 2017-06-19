package com.hdwang.service.datajpa;

import com.hdwang.dao.datajpa.firstDs.UserRepository;
import com.hdwang.entity.dbFirst.User;
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
@Service("userService2")
@Transactional("firstTransactionManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(int id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User findByNumber(String number) {
        return this.userRepository.findByNumber(number);
    }

    @Override
    public List<User> findAllUserByPage(int page,int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User> users =  this.userRepository.findAll(pageable);
        return users.getContent();
    }

    @Override
    public User updateUser(User user,boolean throwEx) {
        User userNew = this.userRepository.save(user);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return userNew;
    }

    @Override
    public void deleteUser(int id) {
        this.userRepository.deleteUser(id);
    }
}
