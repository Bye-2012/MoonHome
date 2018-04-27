package com.moon.msupport.util.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Moon
 * Date: 2018/4/27
 * Desc: JSON解析
 */

public class JsonUtil {

    private JsonUtil() {
    }

    private static Gson getParser() {
        return Holder.INSTANCE;
    }

    public static String toJson(Object obj) {
        return getParser().toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return getParser().fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return getParser().fromJson(json, typeOfT);
    }

    private static final class Holder {
        private static final Gson INSTANCE = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();
    }
}
