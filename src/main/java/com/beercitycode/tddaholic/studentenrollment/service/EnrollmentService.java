package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import com.beercitycode.tddaholic.studentenrollment.repository.EnrollmentRepository;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repository;

    public List<Enrollment> findEnrollmentsByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    public Set<Long> findCompletedCoursesByStudentId(Long studentId) {
        return repository.findCompletedCoursesByStudentId(studentId);
    }

    public void enrollStudentInCourse(Student persistedStudent, Course persistedCourse) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(persistedStudent);
        enrollment.setCourse(persistedCourse);
        enrollment.setCompleted(false);

        Enrollment persistedEnrollment = repository.save(enrollment);

        return;
    }
}
