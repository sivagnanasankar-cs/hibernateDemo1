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

        StudentService studentService = StudentService.getInstance();
        Gson gson = GsonUtil.getGson();
        Student Student = gson.fromJson(requestData, Student.class);
        Student = studentService.addStudent(Student);

        resp.setContentType(ResponseConstants.ContentType.APPLICATION_JSON);
        resp.setStatus(HttpServletResponse.SC_CREATED);

        PrintWriter writer = resp.getWriter();
        writer.write("Student added successfully");
        writer.close();
    }
}
