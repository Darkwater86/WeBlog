package com.darkwater.controller;

import com.alibaba.fastjson.JSON;
import com.darkwater.model.ErrObject;
import com.darkwater.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    private ErrObject errobj;

    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "password", required = false) String password,
                        HttpServletResponse response) {
        errobj = new ErrObject();
        if (!(userService.checkLoginName(name) && userService.checkLoginPassword(password))) {
            errobj.setErrno(-1);
            errobj.setErrmsg("用户名或密码不符合规范");
            return JSON.toJSONString(errobj);
        }
        System.out.println("controller中name"+name);
        String ret = userService.login(name, password);
        if (null == ret) {
            errobj.setErrno(-1);
            errobj.setErrmsg("用户名或密码错误");
            return JSON.toJSONString(errobj);
        }
        errobj.setErrno(0);
        errobj.setErrmsg(ret);
        Cookie cookie = new Cookie("ticket", ret);
        cookie.setPath("/");
        response.addCookie(cookie);
        return JSON.toJSONString(errobj);
    }

    @PostMapping("/register")
    @ResponseBody
    public String CreatUser(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "password", required = false) String password,
                            HttpServletResponse response) {
        errobj = new ErrObject();
        if (!(userService.checkLoginName(name) && userService.checkLoginPassword(password))) {
            errobj.setErrno(-1);
            errobj.setErrmsg("用户名或密码不符合规范");
            return JSON.toJSONString(errobj);
        }
        String ret = userService.register(name, password);
        if (0 == StringUtils.compare("name been used", ret)) {
            errobj.setErrno(-1);
            errobj.setErrmsg("名字已被使用");
            return JSON.toJSONString(errobj);
        }
        errobj.setErrno(0);
        errobj.setErrmsg(ret);
        Cookie cookie = new Cookie("ticket", ret);
        cookie.setPath("/");
        response.addCookie(cookie);
        return JSON.toJSONString(errobj);
    }

    @GetMapping("/relogin")
    public String relogin(Model model,
                          @RequestParam(value = "callback", required = false) String callback) {
        model.addAttribute("callback", callback);
        return "/";
    }
}
