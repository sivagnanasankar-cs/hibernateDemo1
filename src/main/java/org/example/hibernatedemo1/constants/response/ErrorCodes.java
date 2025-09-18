package org.example.hibernatedemo1.constants.response;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public enum ErrorCodes {
    INVALID_DEGREE_DATA("ERR001", "Invalid degree data provided"),


    // student
    INVALID_STUDENT_DATA("ERR002", "Invalid student data provided"),

    ;

    private final String errorCode;
    private final String errorDescription;

    ErrorCodes(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
