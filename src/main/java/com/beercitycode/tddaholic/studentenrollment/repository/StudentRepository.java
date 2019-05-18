package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
