package org.example.hibernatedemo1.dao;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.model.Degree;
import org.example.hibernatedemo1.util.CommonUtil;
import org.example.hibernatedemo1.util.GsonUtil;
import org.example.hibernatedemo1.util.HibernateUtil;
import org.hibernate.Hibernate;
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

    public Degree getDegree(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Degree degree = session.get(Degree.class, id);

            if  (CommonUtil.isValid(degree)) {
                Hibernate.initialize(degree.getDepartments());
            } else {
                log.error("Degree with id {} is not present", id);
                throw new IllegalArgumentException("Degree with id " + id + " is not present");
            }

            return degree;
        } catch (Exception e) {
            log.error("Exception in fetching degree - ", e);
            throw new RuntimeException(e);
        }
    }

    public Degree deleteDegree(Integer id) {
        log.info("Deleting degree with id {}", id);
        Transaction transaction = null;
        Degree degree = this.getDegree(id);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction =  session.beginTransaction();
            session.remove(degree);
            transaction.commit();
            return degree;
        } catch (Exception e) {
            log.error("Exception in deleting degree - ", e);
            if (CommonUtil.isValid(transaction) && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception e1) {
                    log.error("Exception in rollback while deleting - ", e1);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
