package org.example.hibernatedemo1.service;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.dao.StudentDAO;
import org.example.hibernatedemo1.model.Student;
import org.example.hibernatedemo1.util.CommonUtil;

@Slf4j
public class StudentService {

    private static StudentService instance = null;

    private StudentService() {}

    public static StudentService getInstance() {
        if (!CommonUtil.isValid(instance)) {
            instance = new StudentService();
        }
        return instance;
    }

    public Student addStudent(Student std) {
        log.info("Adding student {}", std.toString());
        return StudentDAO.getInstance().addStudent(std);
    }
}
