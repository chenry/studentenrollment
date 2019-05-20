package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.exception.StudentNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.fixtures.CourseFixture;
import com.beercitycode.tddaholic.studentenrollment.fixtures.StudentFixture;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StudentEnrollmentServiceTest {

  @Autowired
  private StudentEnrollmentService service;

  /**
   * Scenario: Unknown Student wants to enroll in a course
   *
   * Given an unknown student
   * When that student enrolls in the course
   * Then an exception should be thrown stating the student is unknown
   */
  @Test
  public void testEnrollStudentInCourse_UnknownStudent() {
    Student student = StudentFixture.create(-100L);
    Course course = CourseFixture.create(-100L);

    try {
      service.enrollStudentInCourse(student, course);
      Assert.fail("Should have thrown an exception");
    } catch (StudentNotFoundException e) {

    }
  }

  /**
   * Scenario: Student with bad credit wants to enroll in a course
   *
   * Given a student has bad credit
   * When that student enrolls in the course
   * Then they should not be able to enroll since they have bad credit
   */

  /**
   * Scenario: Student wants to enroll in a full course
   *
   * Given a student with good credit
   * And the course they want to enroll in is full
   * When that student enrolls in the course
   * Then they should not be allowed to enroll in the course
   */

  /**
   * Scenario: Student wants to enroll in a course, but does not have the prerequisites
   *
   * Given a student with good credit
   * And the course that they want to enroll in has openings
   * And the student has not fulfilled all of the prerequisites
   * When that student enrolls in the course
   * Then they should not be allowed to enroll in the course
   */

  /**
   * Scenario: Happy Case
   *
   * Given a student with good credit
   * And the course that they want to enroll in has openings
   * And the student has fulfilled all of the prerequisites
   * When that student enrolls in the course
   * Then they should be allowed to enroll in the course
   */
}