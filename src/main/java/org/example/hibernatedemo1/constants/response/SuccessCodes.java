package org.example.hibernatedemo1.constants.response;

import lombok.Getter;

@Getter
public enum SuccessCodes {
    DEGREE_ADDED_SUCCESSFULLY(200, "Degree added successfully"),
    DEGREE_UPDATED_SUCCESSFULLY(200, "Degree updated successfully"),

    // student
    STUDENT_ADDED_SUCCESSFULLY(200, "Student added successfully"),

    ;

    private final Integer code;
    private final String message;

    SuccessCodes(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}