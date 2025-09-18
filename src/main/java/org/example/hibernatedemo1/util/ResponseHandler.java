package org.example.hibernatedemo1.util;

import com.google.gson.JsonObject;
import org.example.hibernatedemo1.constants.response.ErrorCodes;
import org.example.hibernatedemo1.constants.response.SuccessCodes;

public class ResponseHandler {

    public static JsonObject buildSuccessResponse(SuccessCodes code) {
        JsonObject response = new JsonObject();
        response.addProperty("status", code.getCode());
        response.addProperty("message", code.getMessage());
        return response;
    }

    public static JsonObject buildErrorResponse(ErrorCodes code) {
        JsonObject response = new JsonObject();
        response.addProperty("errorCode", code.getErrorCode());
        response.addProperty("errorMessage", code.name());
        response.addProperty("description", code.getErrorDescription());
        return response;
    }
}
