package com.darkwater.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Darkwater on 2017/7/22.
 */
public class ErrObject {
    private int errno = 0;
    private String errmsg = "";
    private Map<String,Object> objs = new HashMap<>();
    public int getErrno(){
        return errno;
    }
    public void setErrno(int errno){
        this.errno = errno;
    }
    public String getErrmsg(){
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object get(String name){
        return objs.get(name);
    }
    public void set(String name, Object obj){
        objs.put(name,obj);
    }
}
