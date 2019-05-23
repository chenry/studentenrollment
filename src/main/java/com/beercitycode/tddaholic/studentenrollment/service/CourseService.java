package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.repository.CourseRepository;
import com.beercitycode.tddaholic.studentenrollment.repository.EnrollmentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;


    public Optional<Course> findCourseById(Long courseId) {
        return repository.findById(courseId);
    }

    public List<Course> findCoursePrerequisites(Course course) {
        return repository.findCoursePrerequisitesByCourseId(course.getId());
    }
}
