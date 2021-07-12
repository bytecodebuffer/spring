package com.bai.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

public class RedisUtils {

    private static final String LOCK_SUCCESS = "OK";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param jedis
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis,String key,String value,int expireTime){
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTime);
        String result = jedis.set(key,value,setParams);
        if(LOCK_SUCCESS.equals(result)){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 释放分布式锁
     * @param jedis
     * @param key
     * @param value
     * @return
     */
    public static boolean releaseDistributedLock(Jedis jedis,String key,String value){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key),Collections.singletonList(value));
        if(RELEASE_SUCCESS.equals(result)){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        boolean res1 = tryGetDistributedLock(jedis,"lock","1",1000);
        System.out.println("lock:"+res1);
        boolean res2 = releaseDistributedLock(jedis,"lock","1");
        System.out.println("unlock:"+res2);
    }

}
