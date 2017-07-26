package com.darkwater.model;

import org.springframework.stereotype.Component;

/**
 * Created by Mr.Darkwater on 2017/7/25.
 */
/***
 * 添加ErrHolder可以在Controller/Service层同时修改这个错误对象
 */
@Component
public class ErrHolder {
    private static ThreadLocal<ErrObject> errs = new ThreadLocal<>();
    public void setErrobj(ErrObject errobj){
        errs.set(errobj);
    }
    public ErrObject getErrobj(){
        return errs.get();
    }
    public void clear(){
        errs.remove();
    }
}
