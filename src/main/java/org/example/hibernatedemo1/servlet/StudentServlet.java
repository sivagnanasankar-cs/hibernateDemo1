package org.example.hibernatedemo1.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.hibernatedemo1.constants.ResponseConstants;
import org.example.hibernatedemo1.model.Student;
import org.example.hibernatedemo1.service.StudentService;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.GsonUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@WebServlet("/api/student")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestData = CommonUtil.readFromReader(req.getReader());
        resp.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        Student student = GsonUtil.fromJson(requestData, Student.class);
        if (CommonUtil.isValid(student)) {
            student = StudentService.getInstance().addStudent(student);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            PrintWriter writer = resp.getWriter();
            writer.write("Invalid student data");
            writer.close();
            return;
        }
        resp.setStatus(HttpServletResponse.SC_CREATED);
        PrintWriter writer = resp.getWriter();
        writer.write("Student added successfully");
        writer.close();
    }
}
