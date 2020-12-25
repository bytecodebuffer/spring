package top.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * json工具类
 *
 * @author lgs
 */
public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将任意对象转变为json字符串
     */
    public static <T> String toJson(T object) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.info(e.getLocalizedMessage());
        }
        return json;
    }

    /**
     * 将一个对象数据复制到另一个对象中
     *
     * @param object 源对象
     * @param tClass 目标对象的Class
     * @param <T>    目标对象泛型
     * @param <E>    源对象泛型
     * @return
     */
    public static <T, E> T copyObject(E object, Class<T> tClass) {
        String json = toJson(object);
        T returnObj = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            returnObj = objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            logger.info(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage());
        }
        return returnObj;
    }

    /**
     * 将任意对象转变为map
     */
    public static <T> Map<String, Object> toMap(T object) {
        Map<String, Object> map = null;
        try {
            String json = toJson(object);
            map = objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            logger.info(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage());
        }
        return map;
    }

    /**
     * 将json字符串转变为任意对象（单一对象）
     */
    public static <T> T toObject(String json, Class<T> tClass) {
        T object = null;
        try {
            object = objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            logger.info(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage());
        }
        return object;
    }

    /**
     * 将map转变为任意对象（单一对象）
     */
    public static <T> T toObject(Map<String, Object> map, Class<T> tClass) {
        T object = null;
        try {
            String json = toJson(map);
            object = objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            logger.info(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage());
        }
        return object;
    }

    /**
     * 将json字符串转变为对象集合（json字符串为对象数组字符串）
     */
    public static <T> List<T> toList(String json, Class<T> elementClass) {
        List<T> list = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, elementClass);
            list = objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            logger.info(e.getLocalizedMessage());
        } catch (IOException e) {
            logger.info(e.getLocalizedMessage());
        }
        return list;
    }

    /**
     * 将任意对象转换为MultiValueMap
     *
     * @param object 要转换的对象
     * @param <T>    泛型，对象为任意类型
     * @return
     */
    public static <T> MultiValueMap<String, Object> toMultiValueMap(T object) {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, Object> map = toMap(object);
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            multiValueMap.add(key, map.get(key));
        }
        return multiValueMap;
    }

}
