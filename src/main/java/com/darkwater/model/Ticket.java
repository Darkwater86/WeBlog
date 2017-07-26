package com.darkwater.model;

import java.util.Date;

/**
 * Created by Mr.Darkwater on 2017/7/22.
 */

public class Ticket {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userId) {
        this.userid = userId;
    }

    public Date getEmpried() {
        return empried;
    }

    public void setEmpried(Date empried) {
        this.empried = empried;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    private int id;
    private int userid;
    private Date empried;
    private String ticket;
    private int status;

}
