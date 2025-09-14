package org.example.hibernatedemo1.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;

@Slf4j
public class CommonUtil {

    public static boolean isValid(Object obj) {
        return obj != null && !obj.toString().isEmpty();
    }

    public static String readFromReader(BufferedReader reader) {
        StringBuilder jsonBuilder = new StringBuilder();
        try {
            while (reader.ready()) {
                jsonBuilder.append(reader.readLine());
            }
        } catch (Exception e) {
            log.error("Exception in reading request data ", e);
        }
        return jsonBuilder.toString();
    }

}
