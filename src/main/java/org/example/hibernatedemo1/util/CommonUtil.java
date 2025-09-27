package org.example.hibernatedemo1.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.constants.ResponseConstants;
import org.example.hibernatedemo1.constants.response.ErrorCodes;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
public class CommonUtil {

    public static boolean isValid(Object obj) {
        return obj != null;
    }

    public static boolean isValid(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isValid(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isValid(Object[] array) {
        return array != null;
    }

    public static boolean isNonEmptyArray(Object[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isNonEmptyJSONObject(JsonObject jsonObject) {
        return jsonObject != null && !jsonObject.entrySet().isEmpty();
    }
    public static boolean isNonEmptyJSONArray(JsonArray jsonArray) {
        return jsonArray != null && !jsonArray.isEmpty();
    }

    public static String readFromReader(BufferedReader reader) {
        try(BufferedReader bufferedReader = reader) {
            return bufferedReader.lines().collect(Collectors.joining());
        }catch (Exception e) {
            log.error("Exception in reading from reader", e);
            throw new RuntimeException(e);
        }
    }

}
