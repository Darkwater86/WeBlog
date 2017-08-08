package com.darkwater.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Mr.Darkwater on 2017/8/1.
 */
@Aspect
@Component
public class LoginAspect {
    Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Before("execution(* com.darkwater.controller.LoginController.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.error("*************" + joinPoint.getSignature().toLongString() +
                joinPoint.getArgs() +
                joinPoint.getSourceLocation() +
                joinPoint.getClass() + "***********");
    }

    @Around("execution(* com.darkwater.controller.LoginController.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> clazz = pjp.getTarget().getClass();
        String method = pjp.getSignature().getName();
        Method m = clazz.getMethod(method, ((MethodSignature) pjp.getSignature()).getParameterTypes());
        boolean required = m.getAnnotation(LoginRequired.class).required();//这里是 先拿到注解对象，在从注解对象中拿到注解的属性
        if (required == true) {

        }
        return pjp.proceed();
    }

    @After("execution(* com.darkwater.controller.LoginController.*(..))")
    public void after() {

    }
}
