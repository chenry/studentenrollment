package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    List<Enrollment> findByCourseId(Long courseId);

    @Query("select e.course.id from Enrollment e where e.student.id = :studentId and e.isCompleted = true")
    Set<Long> findCompletedCoursesByStudentId(@Param("studentId") Long studentId);
}
