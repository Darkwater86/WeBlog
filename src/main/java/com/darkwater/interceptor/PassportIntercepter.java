package com.darkwater.interceptor;

import com.alibaba.fastjson.JSON;
import com.darkwater.dao.UserDao;
import com.darkwater.model.ErrHolder;
import com.darkwater.model.HostHolder;
import com.darkwater.model.Ticket;
import com.darkwater.model.User;
import com.darkwater.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Mr.Darkwater on 2017/7/22.
 */
@Component
public class PassportIntercepter implements HandlerInterceptor {
    @Autowired
    TicketService ticketService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    ErrHolder errHolder;

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        if (null != httpServletRequest.getCookies()) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (null != ticket) {
            Ticket loginTicket = ticketService.getTicketByTicket(ticket);
            if (null == loginTicket || loginTicket.getEmpried().before(new Date()) || 0 != loginTicket.getStatus()) {
            }else {
                User user = userDao.selectById(loginTicket.getUserid());
                hostHolder.setUser(user);
            }
        }
        if (null == hostHolder.getUser()) {
            httpServletResponse.setCharacterEncoding("UTF-8");
            errHolder.getErrobj().setErrmsg("用户未登录"+httpServletRequest.getRequestURI());
            errHolder.getErrobj().setErrno(-1);
            httpServletResponse.getWriter().print(JSON.toJSONString(errHolder.getErrobj()));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (null != modelAndView) {
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}