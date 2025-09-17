package org.example.hibernatedemo1.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.model.Degree;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.GsonUtil;
import org.example.hibernatedemo1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
public class DegreeDAO {

    private static DegreeDAO instance = null;

    private DegreeDAO() {}

    public static DegreeDAO getInstance() {
        if (!CommonUtil.isValid(instance)) {
            instance = new DegreeDAO();
        }
        return instance;
    }

    public Degree addDegree(Degree degree) {
        log.info("Adding degree {}", GsonUtil.toJson(degree));
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction =  session.getTransaction();
            session.persist(degree);
            transaction.commit();
            return degree;
        } catch (Exception e) {
            if (CommonUtil.isValid(transaction) && transaction.isActive()) {
                transaction.rollback();
            }
            log.error("Exception in adding degree - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
