package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.List;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testFindStudentById() {
    Student student = repository.findStudentById(UUID.randomUUID());

    Assert.assertNull(student);
  }

  @Test
  public void testFindStudentById_StudentExists() {
    UUID studentId = createStudent();

    Student student = repository.findStudentById(studentId);
    Assert.assertNotNull(student);
  }

  /* ================================================================== */

  private UUID createStudent() {
    UUID uuid = UUID.randomUUID();
    String updateQuery = String.format("insert into student (ID, FIRST_NAME, LAST_NAME) values ('%s', 'CARLUS', 'HENRY')", uuid.toString());
    jdbcTemplate.update(updateQuery);

    return uuid;
  }
}
