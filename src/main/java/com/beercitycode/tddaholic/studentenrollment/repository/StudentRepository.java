package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Student findStudentById(UUID id) {
    String query = String.format("select id, first_name, last_name from student where id = '%s'", id);
    return jdbcTemplate.queryfor
  }
}
