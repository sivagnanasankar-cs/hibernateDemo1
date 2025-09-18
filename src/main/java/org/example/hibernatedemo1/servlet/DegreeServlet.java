package org.example.hibernatedemo1.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.model.Degree;
import org.example.hibernatedemo1.service.DegreeService;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.GsonUtil;

import java.io.IOException;

@WebServlet("/api/degree")
@Slf4j
public class DegreeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = CommonUtil.readFromReader(request.getReader());
        Degree degree = GsonUtil.fromJson(json, Degree.class);
        log.info("Adding degree {}", degree);
        if (!CommonUtil.isValid(degree)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid degree data");
            return;
        }
        degree = DegreeService.getInstance().addDegree(degree);
        response.setContentType("application/json");
        response.getWriter().write(GsonUtil.toJson(degree));
        response.getWriter().close();
    }
}
