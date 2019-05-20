package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repository;

    public List<Enrollment> findEnrollmentsByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
