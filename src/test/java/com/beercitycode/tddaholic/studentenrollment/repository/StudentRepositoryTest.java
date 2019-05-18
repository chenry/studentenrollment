package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.fixtures.Fixture;
import com.beercitycode.tddaholic.studentenrollment.fixtures.StudentFixture;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository repository;

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Autowired
  private Fixture fixture;

  @Test
  public void testFindStudentById() {
    Optional<Student> student = null;
    student = repository.findById(-100L);

    Assert.assertFalse(student.isPresent());
  }

  @Test
  public void testFindStudentById_StudentExists() {
    Long studentId = -100L;
    fixture.createAndPersistStudent(studentId);
    fixture.showRecords("student");

    Optional<Student> student = repository.findById(studentId);
    Assert.assertTrue(student.isPresent());

  }

  /* ================================================================== */

}
