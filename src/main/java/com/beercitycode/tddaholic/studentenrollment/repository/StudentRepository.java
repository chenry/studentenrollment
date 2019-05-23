package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
