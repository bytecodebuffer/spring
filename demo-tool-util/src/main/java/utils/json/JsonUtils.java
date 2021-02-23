package utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author bz
 * @date 2021/2/23
 */
public class JsonUtils {
    public static String obj2json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static String obj2json(Object obj, boolean prettyFormat) {
        return JSON.toJSONString(obj, prettyFormat);
    }

    public static Map<String, Object> obj2Map(Object obj) {
        Object jsonObject = JSON.toJSON(obj);
        return jsonObject instanceof JSONObject ? ((JSONObject)jsonObject).getInnerMap() : null;
    }

    public static <T> T json2obj(String jsonStr, Class<T> clazz) {
        try {
            return JSON.parseObject(jsonStr, clazz);
        } catch (Exception var3) {
            return null;
        }
    }

    public static Map<String, Object> json2Map(String jsonStr) {
        try {
            return (Map)JSON.parseObject(jsonStr, Map.class);
        } catch (Exception var2) {
            return null;
        }
    }

    public static <T> List<T> json2List(String jsonStr, Class<T> clazz) {
        Object list = new ArrayList();

        try {
            list = JSON.parseArray(jsonStr, clazz);
        } catch (Exception var4) {
        }

        return (List)list;
    }

    public static List<Map<String, Object>> json2MapList(String jsonString) {
        Object list = new ArrayList();

        try {
            list = (List)JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
            }, new Feature[0]);
        } catch (Exception var3) {
        }

        return (List)list;
    }
}
