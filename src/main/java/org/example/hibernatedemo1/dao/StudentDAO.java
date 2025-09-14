package org.example.hibernatedemo1.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.model.Student;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
public class StudentDAO {

    private static StudentDAO instance = null;

    private StudentDAO() {}

    public static StudentDAO getInstance() {
        if (!CommonUtil.isValid(instance)) {
            instance = new StudentDAO();
        }
        return instance;
    }

    public Student addStudent(Student student) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (CommonUtil.isValid(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Exception in adding student - {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return student;
    }
}
