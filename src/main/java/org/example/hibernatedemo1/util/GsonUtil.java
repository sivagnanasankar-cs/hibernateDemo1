package org.example.hibernatedemo1.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static Gson gson = null;

    public static Gson getGson() {
        if (!CommonUtil.isValid(gson)) {
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
        }
        return gson;
    }
}
