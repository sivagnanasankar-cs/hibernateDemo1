package org.example.hibernatedemo1.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.model.Degree;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.GsonUtil;
import org.example.hibernatedemo1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Slf4j
public class DegreeDAO {

    private static DegreeDAO instance = null;

    private static final Logger log = LoggerFactory.getLogger(DegreeDAO.class);

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
            transaction =  session.beginTransaction();
            session.persist(degree);
            transaction.commit();
            return degree;
        } catch (Exception e) {
            log.error("Exception in adding degree - ", e);
            if (CommonUtil.isValid(transaction) && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception e1) {
                    log.error("Exception in rollback while updating - ", e1);
                }
            }
            throw new IllegalArgumentException(e);
        }
    }

    public Degree updateDegree(Degree degree) {
        log.info("Updating degree {}", GsonUtil.toJson(degree));
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction =  session.beginTransaction();
            session.merge(degree);
            transaction.commit();
            return degree;
        } catch (Exception e) {
            log.error("Exception in updating degree - ", e);
            if (CommonUtil.isValid(transaction) && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception e1) {
                    log.error("Exception in rollback while updating - ", e1);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
