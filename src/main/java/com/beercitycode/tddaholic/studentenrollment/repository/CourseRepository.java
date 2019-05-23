package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

  @Query("select c from Course c join CoursePrerequisite cp on cp.prereqCourse.id = c.id where cp.course.id = :id")
  List<Course> findCoursePrerequisitesByCourseId(@Param("id") Long id);
}
