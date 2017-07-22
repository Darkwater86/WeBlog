package com.darkwater.service;

import com.darkwater.dao.UserDao;
import com.darkwater.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User selectUserById(int id){
        return userDao.selectById(id);
    }

    public User selectUserByName(String name){
        return userDao.selectByName(name);
    }

    public int createUser(String name,String password){
        return userDao.insertUser(new User(name,password,"a","a"));
    }
}
