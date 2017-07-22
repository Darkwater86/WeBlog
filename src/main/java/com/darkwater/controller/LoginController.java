package com.darkwater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Controller
public class LoginController {
    @GetMapping("/")
    public String login(){
        return "register";
    }
}
