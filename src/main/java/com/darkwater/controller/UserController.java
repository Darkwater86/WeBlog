package com.darkwater.controller;

import com.darkwater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("/s")
//    public String getUserById(Model model){
//        model.addAttribute("user",userService.selectUserById(1));
//        return "success";
//    }
    @RequestMapping("/")
    public String homePage(Model model){
        return "detail";
    }

    @GetMapping("/s")
    public String getUserByName(Model model,
                              @RequestParam(value = "name",required = false)String name){
        model.addAttribute("user",userService.selectUserByName(name));
        return "success";
    }
}
