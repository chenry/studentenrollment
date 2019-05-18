package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.CoursePrerequisite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursePrerequisiteRepository extends CrudRepository<CoursePrerequisite, Long> {
}
