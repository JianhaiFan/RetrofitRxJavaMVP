package com.xiaofan.retrofitrxjavamvp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author: 范建海
 * @createTime: 2016/10/24 10:32
 * @className:  GsonUtil
 * @description: Gson解析相关工具类
 * @changed by:
 */
public class GsonUtil {
    // Gson实体
//    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS").create();
    private static Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
            .create();
    /**
     *  JSON串解析成实体Bean
     * @param json  待解析的json串
     * @param cls   对应实体Bean的class类型
     * @param <T>   泛型
     * @return      实体Bean
     */
    public static <T> T json2Object(String json, Class<T> cls) {
        return gson.fromJson(json,cls);
    }

    /**
     * 实体(对象/集合)转换成JSON串
     * @param obj 带转换的对象
     * @return  JSON串
     */
    public static String object2Json(Object obj) {
        return gson.toJson(obj);
    }

}
