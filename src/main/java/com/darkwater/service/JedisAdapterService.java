package com.darkwater.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Mr.Darkwater on 2017/7/29.
 */
@Component
public class JedisAdapterService implements InitializingBean {
    private JedisPool jedisPool = null;
    private Jedis jedis = null;
    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("127.0.0.1", 6379);
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    public void zadd(String key, double score, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zadd(key, score, member);
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public Set<String> zrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void zrem(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.zrem(key);
        } catch (Exception e) {
            System.out.print(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long srem(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key, member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //将list中的k/v打印出来
    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        List<String> list = new ArrayList<>();
        try {
            jedis = jedisPool.getResource();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return list;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    //Redis对象存储
    public void setObject(String key, Object object) {
        set(key, JSON.toJSONString(object));
    }

    public <T> T getObject(String key, Class<T> clazz) {
        if (key != null) {
            return JSONObject.parseObject(key, clazz);
        }
        return null;
    }

}

