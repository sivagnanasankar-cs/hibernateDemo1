package org.example.hibernatedemo1.service;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.dao.DegreeDAO;
import org.example.hibernatedemo1.model.Degree;
import org.example.hibernatedemo1.util.CommonUtil;

@Slf4j
public class DegreeService {

    private static DegreeService instance = null;

    private DegreeService() {}

    public static DegreeService getInstance() {
        if (!CommonUtil.isValid(instance)) {
            instance = new DegreeService();
        }
        return instance;
    }

    public Degree addDegree(Degree degree) {
        log.info("Adding degree {}", degree.toString());
        return DegreeDAO.getInstance().addDegree(degree);
    }
}
