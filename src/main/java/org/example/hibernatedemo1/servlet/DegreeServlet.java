package org.example.hibernatedemo1.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.MessageCodes;
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
        log.info("Updating degree {}", degree);
        response.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        if (!CommonUtil.isValid(degree)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print(ResponseHandler.buildErrorResponse(ErrorCodes.INVALID_DEGREE_DATA));
            return;
        }
        DegreeService.getInstance().updateDegree(degree);
        response.getWriter().println(ResponseHandler.buildSuccessResponse(SuccessCodes.DEGREE_UPDATED_SUCCESSFULLY));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter(MessageCodes.ACTION);
        response.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        if (action.equals("fetchsingledegree")) {
            Integer degreeId =  CommonUtil.isValid(request.getParameter("degreeId")) ?
                    Integer.parseInt(request.getParameter("degreeId")) : null;
            if (CommonUtil.isValid(degreeId)) {
                Degree degree = DegreeService.getInstance().getDegree(degreeId);
                if (CommonUtil.isValid(degree)) {
                    String response1 = GsonUtil.toJson(degree);
                    response.getWriter().print(response1);
                }
            } else {
                log.error("Degree Id is empty for fetchsingledegree");
                response.getWriter().println(ResponseHandler.buildErrorResponse(ErrorCodes.DEGREE_ID_IS_EMPTY));
            }
        } else if (action.equals("fetchdegrees")) {
            // TODO : implement
            Integer pageNo = request.getParameter("pageNo") != null ? Integer.parseInt(request.getParameter("pageNo")) : 1;

        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String degreeId = request.getParameter("degreeId");
        response.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        if (CommonUtil.isValid(degreeId)) {
            Integer id = Integer.parseInt(degreeId);
            DegreeService.getInstance().deleteDegree(id);
            response.getWriter().println(ResponseHandler.buildSuccessResponse(SuccessCodes.DEGREE_DELETED_SUCCESSFULLY));
        } else {
            log.error("Degree Id is empty for delete or not valid json for delete request");
            response.getWriter().println(ResponseHandler.buildErrorResponse(ErrorCodes.DEGREE_ID_IS_EMPTY));
        }
    }
}
