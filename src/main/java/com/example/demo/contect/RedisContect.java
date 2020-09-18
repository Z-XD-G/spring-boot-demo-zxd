package com.example.demo.contect;

import redis.clients.jedis.Jedis;

/**
 * 单例模式连接Redis
 */
public class RedisContect {

    private volatile static RedisContect redisContect;
    private volatile Jedis jedisInstance;

    private RedisContect(){}

    public Jedis getJedisInstance() {
        return jedisInstance;
    }

    private void setJedisInstance(Jedis jedisInstance) {
        this.jedisInstance = jedisInstance;
    }

    public static RedisContect getInstance(){
        if(redisContect == null){
            synchronized (RedisContect.class){
                if(redisContect == null){
                    redisContect = new RedisContect();
                    redisContect.setJedisInstance(new Jedis("localhost", 6379));
                }
            }
        }
        return redisContect;
    }

}
