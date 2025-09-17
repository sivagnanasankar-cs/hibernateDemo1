package org.example.hibernatedemo1.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class GsonUtil {


    private static final Gson gson;

    static {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public static Gson getGson() {
        return gson;
    }

    public static String toJson(Object obj) {
        return CommonUtil.isValid(obj) ? gson.toJson(obj) : null;
    }

    public static JsonObject toJsonObject(Object obj) {
        return CommonUtil.isValid(obj) ? gson.toJsonTree(obj).getAsJsonObject() : null;
    }

    public static <T> T fromJson(String json, Class<T> tclass) {
        return CommonUtil.isValid(json) ? gson.fromJson(json, tclass) : null;
    }

}
