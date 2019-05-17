package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Student findStudentById(UUID id) {
    String query = String.format("select id, first_name, last_name from student where id = '%s'", id);
    return jdbcTemplate.query(query, new RowMapper<Student>() {

      @Override
      public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        Student s = new Student();
        s.setId(UUID.fromString(resultSet.getString("id")));
        s.setLastName(resultSet.getString("last_name"));
        s.setFirstName(resultSet.getString("first_name"));
        return s;
      }
    });
  }
}
