package top.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author lgs
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long DEFAULT_EXPIRE_TIME = -1L;

    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    /**
     * 将值存入redis
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间,单位：毫秒
     * @param <T>        任意类型的数据都可作为值
     */
    public <T> void set(String key, T value, long expireTime) {
        if (value == null) {
            return;
        }
        String jsonData = JsonUtils.toJson(value);
        redisTemplate.opsForValue().set(key, jsonData);
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 将值存入redis
     *
     * @param key   键
     * @param value 值
     * @param <T>   任意类型的数据都可作为值
     */
    public <T> void set(String key, T value) {
        set(key, value, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 检查是否含有指定值
     *
     * @param key 键
     */
    public boolean hasData(String key) {
        try {
            Boolean has = redisTemplate.hasKey(key);
            return has == null ? false : has;
        } catch (Exception e) {
            logger.error(e.toString());
            return false;
        }
    }

    /**
     * 取出普通值，并更新过期时间
     *
     * @param key        键
     * @param clazz      值的数据类型
     * @param expireTime 过期时间，单位：毫秒
     * @param <T>        可用任意数据类型接收值
     * @return
     */
    public <T> T get(String key, Class<T> clazz, long expireTime) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        }
        return JsonUtils.toObject(value, clazz);
    }

    /**
     * 取出普通值
     *
     * @param key   键
     * @param clazz 值的数据类型
     * @param <T>   可用任意类型接收数据
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 取出字符串类型的值并重新设置过期时间
     *
     * @param key        键
     * @param expireTime 过期时间
     * @return 普通字符串数据
     */
    public String get(String key, long expireTime) {
        return get(key, String.class, expireTime);
    }

    /**
     * 取出普通字符串数据
     *
     * @param key 键
     * @return
     */
    public String get(String key) {
        return get(key, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 删除一条数据
     *
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 根据键的前缀批量删除数据
     *
     * @param prefix 前缀
     */
    public void deleteBatchByPrefix(String prefix) {
        Set<String> keys = redisTemplate.keys(prefix + "*");
        redisTemplate.delete(keys);
    }

    /**
     * 根据键里面的关键字，模糊匹配删除数据
     *
     * @param likeStr 键里的关键字
     */
    public void deleteBatchByLike(String likeStr) {
        Set<String> keys = redisTemplate.keys("*" + likeStr + "*");
        redisTemplate.delete(keys);
    }
}
