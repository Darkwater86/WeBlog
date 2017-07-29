//package com.darkwater.utils;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import redis.clients.jedis.Jedis;
//
///**
// * Created by Mr.Darkwater on 2017/7/28.
// */
//public class JedisAdapter implements InitializingBean {
//    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
//    }
//
//    public static void print(int index,Object obj){
//        System.out.println(String.format("{%d}%s",index,obj.toString()));
//    }
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost",6379);
//        jedis.flushAll();
//        //get,set
//        jedis.set("hello","world");
//        print(1,jedis.get("hello"));
//        jedis.rename("hello","newhello");
//        print(1,jedis.get("newhello"));
////        jedis.setex("hell02",15,"world");
//
//        //数值操作
//        jedis.set("pv","100");
//        jedis.incr("pv");
//        print(2,jedis.get("pv"));
//        jedis.decrBy("pv",5);
//        print(2,jedis.get("pv"));
//        print(3,jedis.keys("*"));
//        String listName = "list";
//        jedis.del(listName);
//    }
//
//
//
//}
