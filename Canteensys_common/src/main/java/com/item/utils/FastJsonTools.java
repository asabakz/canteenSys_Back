package com.item.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author java实战基地
 * @Version 2383404558
 */
public class FastJsonTools {
    private static SerializeConfig CONFIG = null;

    static {
        CONFIG = new SerializeConfig();
        // 使用json-lib兼容的日期输出格式
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    private static final SerializerFeature[] FEATURES = {
            // 输出空字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * Object 转换成字符串
     *
     * @param object
     * @return JSON 字符串
     */
    public static String toJSONFeaturesString(Object object) {
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    /**
     * Object 转换成字符串
     *
     * @param object
     * @return JSON 字符串
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, CONFIG);
    }

    /**
     * json字符串转map
     *
     * @param s JSON 字符串
     * @return Map 对象
     */
    public static Map<String, Object> strToMap(String s) {
        Map<String, Object> m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * map转json字符串
     *
     * @param m Map 对象
     * @return JSON 字符串
     */
    public static String mapToStr(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    /**
     * 用fastjson 将json字符串解析成一个 JavaBean
     *
     * @param jsonString JSON 字符串
     * @param cls 目标类
     * @return 解析后的对象
     */
    public static <T> T getJson(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 用fastjson将json字符串解析成一个List<JavaBean>及List<String>
     *
     * @param jsonString JSON 字符串
     * @param cls 目标类
     * @return 解析后的列表
     */
    public static <T> List<T> getArrayJson(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json字符串转换成List<String>
     *
     * @param jsonString JSON 字符串
     * @return List<String>
     */
    public static List<String> getArrayJson(String jsonString) {
        List<String> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, String.class); // 指定类型
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json字符串转换成List<Map<String, Object>>
     *
     * @param jsonString JSON 字符串
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getArrayJsonMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

