package utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import utils.json.JsonUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author bz
 * @date 2020/12/24
 */
@Component
public class RedisUtil {
    private static final  Long DEFAULT_EXPIRE_TIME = -1L;

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 判断key 是否存在
     */
    public boolean hasKey(String key){
        try{
            Boolean res = redisTemplate.hasKey(key);
            return res == null?false:res;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将值存入redis
     * @param key
     * @param value
     * @param expireTime
     * @param <T>
     */
    public <T> void set(String key,T value,long expireTime){
        if(value == null){
            return;
        }
        String jsonData = JsonUtils.obj2json(value);
        redisTemplate.opsForValue().set(key,jsonData);
        if(expireTime>0){
            redisTemplate.expire(key,expireTime, TimeUnit.MILLISECONDS);
        }
    }

    public <T> void set(String key,T value){
        set(key,value,DEFAULT_EXPIRE_TIME);
    }

    /**
     * 获取普通值，并更新过期时间
     * @param key
     * @param cls
     * @param expireTime
     * @param <T>
     * @return
     */
    public <T> T get(String key,Class<T> cls,long expireTime){
        String valuie = redisTemplate.opsForValue().get(key);
        if(valuie == null){
            return null;
        }
        if(expireTime>0){
            redisTemplate.expire(key,expireTime,TimeUnit.MILLISECONDS);
        }
        return JsonUtils.json2obj(valuie,cls);
    }


    public <T> T get(String key,Class<T> cls){
        return get(key,cls,DEFAULT_EXPIRE_TIME);
    }

    /**
     * 获取字符串类型并重置过期时间
     * @param key
     * @param expireTime
     * @return
     */
    public String get(String key,long expireTime){
        return get(key,String.class,expireTime);
    }

    /**
     * 获取普通字符串值
     * @param key
     * @return
     */
    public String get(String key){
        return get(key,DEFAULT_EXPIRE_TIME);
    }

    /**
     * 删除数据
     * @param key
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }


    /**
     * 删除前缀的关键字
     * @param prefix
     */
    public void deleteBatchByPrefix(String prefix){
        Set<String> keys = redisTemplate.keys(prefix+"*");
        redisTemplate.delete(keys);
    }

    /**
     * 删除模糊关键字
     * @param likeKey
     */
    public void deleteBatchByLike(String likeKey){
        Set<String> keys = redisTemplate.keys("*" + likeKey + "*");
        redisTemplate.delete(keys);
    }
}
