package org.example.hibernatedemo1.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.hibernatedemo1.constants.response.ErrorCodes;
import org.example.hibernatedemo1.constants.response.SuccessCodes;

import java.util.List;

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

    public static JsonObject buildSuccessResponse(JsonArray jsonArray) {
        JsonObject responseObj = new JsonObject();
        responseObj.addProperty("status", 200);
        responseObj.addProperty("message", "SUCCESS");
        responseObj.add("data", jsonArray);
        return responseObj;
    }

    public static JsonObject buildSuccessResponse(JsonObject jsonObject) {
        JsonObject responseObj = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(jsonObject);

        responseObj.addProperty("status", 200);
        responseObj.addProperty("message", "SUCCESS");
        responseObj.add("data",jsonArray);
        return responseObj;
    }
}
