package com.beercitycode.tddaholic.studentenrollment.service;

import javax.transaction.Transactional;
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
   * Scenario: Unknown Student wants to enroll in a class
   *
   * Given an unknown student
   * When that student enrolls in the class
   * Then an exception should be thrown stating the student is unknown
   */

  /**
   * Scenario: Student with bad credit wants to enroll in a class
   *
   * Given a student has bad credit
   * When that student enrolls in the class
   * Then they should not be able to enroll since they have bad credit
   */

  /**
   * Scenario: Student wants to enroll in a full class
   *
   * Given a student with good credit
   * And the class they want to enroll in is full
   * When that student enrolls in the class
   * Then they should not be allowed to enroll in the class
   */

  /**
   * Scenario: Student wants to enroll in a class, but does not have the prerequisites
   *
   * Given a student with good credit
   * And the class that they want to enroll in has openings
   * And the student has not fulfilled all of the prerequisites
   * When that student enrolls in the class
   * Then they should not be allowed to enroll in the class
   */

  /**
   * Scenario: Happy Case
   *
   * Given a student with good credit
   * And the class that they want to enroll in has openings
   * And the student has fulfilled all of the prerequisites
   * When that student enrolls in the class
   * Then they should be allowed to enroll in the class
   */
}