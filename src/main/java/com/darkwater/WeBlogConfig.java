package com.darkwater;

import com.darkwater.interceptor.ErrInterceptor;
import com.darkwater.interceptor.PassportIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Mr.Darkwater on 2017/7/21.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.darkwater")
public class WeBlogConfig extends WebMvcConfigurerAdapter{

    @Autowired
    PassportIntercepter passportIntercepter;

    @Autowired
    ErrInterceptor errInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errInterceptor).addPathPatterns("/**");
        registry.addInterceptor(passportIntercepter).addPathPatterns("/*");
        super.addInterceptors(registry);
    }
}