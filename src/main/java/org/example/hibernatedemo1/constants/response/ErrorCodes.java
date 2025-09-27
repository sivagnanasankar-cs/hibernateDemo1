package org.example.hibernatedemo1.constants.response;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public enum ErrorCodes {
    INVALID_DEGREE_DATA("ERR_DE_001", "Invalid degree data provided"),
    DEGREE_ID_IS_EMPTY("ERR_DE_002", "Degree id is empty"),


    // student
    INVALID_STUDENT_DATA("ERR_ST_001", "Invalid student data provided"),

    ;

    private final String errorCode;
    private final String errorDescription;

    ErrorCodes(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}
