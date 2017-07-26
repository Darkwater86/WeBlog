package com.darkwater.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mr.Darkwater on 2017/7/22.
 */
public class ViewObject {
    private Map<String,Object> objs = new HashMap<>();

    public Object get(String name){
        return objs.get(name);
    }

    public void set(String name ,Object obj){
        objs.put(name,obj);
    }
}
