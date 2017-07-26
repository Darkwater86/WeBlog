package com.darkwater.service;

import com.darkwater.dao.TicketDao;
import com.darkwater.model.Ticket;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Mr.Darkwater on 2017/7/22.
 */
@Service
public class TicketService {
    @Autowired
    TicketDao ticketDao;


    public String addTicket(int userid){
        Ticket ticket = new Ticket();
        ticket.setEmpried(DateUtils.addDays(new Date(),7));
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        ticket.setUserid(userid);
        ticketDao.createTicket(ticket);
        return  ticket.getTicket();
    }

    public Ticket getTicketByTicket(String ticket){
        Ticket loginTicket = ticketDao.selectTicketByTicket(ticket);
        if (DateUtils.addDays(loginTicket.getEmpried(),7).before(new Date()))
            return null;
        return loginTicket;
    }
}
