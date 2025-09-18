package org.example.hibernatedemo1.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.constants.ResponseConstants;
import org.example.hibernatedemo1.constants.response.ErrorCodes;
import org.example.hibernatedemo1.constants.response.SuccessCodes;
import org.example.hibernatedemo1.model.Degree;
import org.example.hibernatedemo1.service.DegreeService;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.GsonUtil;
import org.example.hibernatedemo1.util.ResponseHandler;

import java.io.IOException;

@WebServlet("/api/degree")
@Slf4j
public class DegreeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = CommonUtil.readFromReader(request.getReader());
        Degree degree = GsonUtil.fromJson(json, Degree.class);
        log.info("Adding degree {}", degree);
        response.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        if (!CommonUtil.isValid(degree)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(ResponseHandler.buildErrorResponse(ErrorCodes.INVALID_DEGREE_DATA));
            return;
        }
        DegreeService.getInstance().addDegree(degree);
        response.getWriter().print(ResponseHandler.buildSuccessResponse(SuccessCodes.DEGREE_ADDED_SUCCESSFULLY));
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = CommonUtil.readFromReader(request.getReader());
        Degree degree = GsonUtil.fromJson(json, Degree.class);
        log.info("Adding degree {}", degree);
        response.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        if (!CommonUtil.isValid(degree)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(ResponseHandler.buildErrorResponse(ErrorCodes.INVALID_DEGREE_DATA));
            return;
        }
        DegreeService.getInstance().updateDegree(degree);
        response.getWriter().println(ResponseHandler.buildSuccessResponse(SuccessCodes.DEGREE_UPDATED_SUCCESSFULLY));
    }

//    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String json = CommonUtil.readFromReader(request.getReader());
//        JsonObject jsonObject = GsonUtil.toJsonObject(json);
//        log.info("Deleting degree {}", jsonObject.get("id"));
//
//
//    }
}
