package com.darkwater.service;

import com.darkwater.dao.UserDao;
import com.darkwater.model.User;
import com.darkwater.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;


/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    TicketService ticketService;

    public User selectUserById(int id) {
        return userDao.selectById(id);
    }

    public User selectUserByName(String name) {
        return userDao.selectByName(name);
    }

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public String login(String name, String password) {
        User user = userDao.selectByName(name);
        if (null == user) {
            logger.error("查无此人");
            return null;
        }
        logger.error("登录名"+name);
        logger.error("登录salt"+user.getSalt());
        String psw  = MD5Util.MD5(password + user.getSalt());
        logger.error("登录密码"+psw);
        if (!psw.equals(user.getPassword())){
            logger.error("密码错误");
            return null;
        }
        return ticketService.addTicket(user.getId());
    }

    public String register(String name, String password) {
        User user = new User();
        if (!checkRegister(name))
            return "name been used";
        user.setSalt(UUID.randomUUID().toString().replaceAll("-", ""));
        logger.error("注册salt"+user.getSalt());
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setPassword(MD5Util.MD5(password + user.getSalt()));
        logger.error("注册密码"+user.getPassword());
        user.setName(name);
        userDao.insertUser(user);
        logger.error("注册数据库salt"+userDao.selectByName(name).getSalt());
        return ticketService.addTicket(userDao.selectByName(name).getId());
    }

    public boolean checkLoginName(String name) {
        if (StringUtils.isBlank(name) || StringUtils.isEmpty(name))
            return false;//是否为空
        if (0 != StringUtils.compare(name, name.trim()))
            return false;//是否包含空格

        if (name.length() < 8 || name.length() > 16) {
            return false;//是否在8到16之间
        }
        return true;
    }

    public boolean checkLoginPassword(String password) {
        if (0 != StringUtils.compare(password, password.trim()))
            return false;//是否包含空格
        if (StringUtils.isBlank(password) || StringUtils.isEmpty(password))
            return false;//是否为空
        if (password.length() < 8 || password.length() > 16) {
            return false;//是否在8到16之间
        }
        return true;
    }

    public boolean checkRegister(String name) {
        if (null != userDao.selectByName(name))
            return false;
        return true;
    }

}
