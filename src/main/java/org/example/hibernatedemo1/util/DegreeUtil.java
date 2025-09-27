package org.example.hibernatedemo1.util;

import lombok.extern.slf4j.Slf4j;
import org.example.hibernatedemo1.model.Degree;

@Slf4j
public class DegreeUtil {

    public static void validatePost(Degree degree) {
        if (!CommonUtil.isValid(degree)) {
            log.error("Degree {} is invalid", degree);
            throw new IllegalArgumentException();
        }
        if (CommonUtil.isValid(degree.getDegreeId())) {
            log.error("Degree id {} should not be present", degree);
            throw new IllegalArgumentException();
        }
        if (!CommonUtil.isValid(degree.getDegreeName())) {
            log.error("Degree name {} is invalid", degree.getDegreeName());
            throw new IllegalArgumentException();
        }
        if (!CommonUtil.isValid(degree.getDegreeCode())) {
            log.error("Degree code {} is invalid", degree.getDegreeCode());
            throw new IllegalArgumentException();
        }
    }

    public static void validatePut(Degree degree) {
        if (!CommonUtil.isValid(degree)) {
            log.error("Degree {} is invalid", degree);
            throw new IllegalArgumentException();
        }
        if (!CommonUtil.isValid(degree.getDegreeId())) {
            log.error("Degree id {} should be present", degree);
            throw new IllegalArgumentException();
        }
        if (!CommonUtil.isValid(degree.getDegreeName())) {
            log.error("Degree name {} is invalid", degree.getDegreeName());
            throw new IllegalArgumentException();
        }
        if (!CommonUtil.isValid(degree.getDegreeCode())) {
            log.error("Degree code {} is invalid", degree.getDegreeCode());
            throw new IllegalArgumentException();
        }
    }
}
