package captcha.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author bz
 * @date 2020/9/14
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 获取对象
     * @param key
     * @return
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }
    /**
     * 缓存
     * @param key
     * @param value
     * @return
     */
    public Boolean set(String key,Object value){
        try{
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置带有过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public Boolean set(String key,Object value,Long time){
        try{
            if(time>0){
                redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
            }else {
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
