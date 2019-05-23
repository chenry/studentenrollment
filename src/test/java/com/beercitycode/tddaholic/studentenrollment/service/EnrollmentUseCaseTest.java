package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.exception.CourseIsAtCapacityException;
import com.beercitycode.tddaholic.studentenrollment.exception.CourseNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentDidNotCompletePrerequisitesException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentHasBadCreditException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.fixtures.CourseFixture;
import com.beercitycode.tddaholic.studentenrollment.fixtures.Fixture;
import com.beercitycode.tddaholic.studentenrollment.fixtures.StudentFixture;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.Arrays;
import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnrollmentUseCaseTest {

    @Autowired
    private EnrollmentUseCase service;

    @Autowired
    private Fixture fixture;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Scenario: Unknown Student wants to enroll in a course
     *
     * Given an unknown student When that student enrolls in the course Then an exception should be
     * thrown stating the student is unknown
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
     * Given a student has bad credit When that student enrolls in the course Then they should not
     * be able to enroll since they have bad credit
     */
    @Test
    public void testEnrollStudentInCourse_StudentHasBadCredit() {
        Student student = fixture.createAndPersistStudent(20);
        Course course = CourseFixture.create(-100L);

        try {
            service.enrollStudentInCourse(student, course);
            Assert.fail("Should have thrown exception");
        } catch (StudentHasBadCreditException ex) {

        }
    }

    /**
     * Scenario: Student with bad credit wants to enroll in a course that doesn't exist
     *
     * Given a student And the course does not exist When that student enrolls in the course Then
     * they should not be able to enroll since the course does not exist
     */
    @Test
    public void testEnrollStudentInCourse_CourseDoesNotExist() {
        Student student = fixture.createAndPersistStudent();
        Course course = CourseFixture.create(-100L);

        try {
            service.enrollStudentInCourse(student, course);
            Assert.fail("Should have thrown exception");
        } catch (CourseNotFoundException ex) {

        }
    }


    /**
     * Scenario: Student wants to enroll in a full course
     *
     * Given a student with good credit And the course they want to enroll in is full When that
     * student enrolls in the course Then they should not be allowed to enroll in the course
     */
    @Test
    public void testEnrollStudentInCourse_CourseIsFull() {
        Student enrolledStudent1 = fixture.createAndPersistStudent();
        Student enrolledStudent2 = fixture.createAndPersistStudent();
        Student enrolledStudent3 = fixture.createAndPersistStudent();

        Course course = fixture.createAndPersistCourse(3);

        fixture.createAndPersistEnrollment(enrolledStudent1, course);
        fixture.createAndPersistEnrollment(enrolledStudent2, course);
        fixture.createAndPersistEnrollment(enrolledStudent3, course);

        Student student = fixture.createAndPersistStudent();

        try {
            service.enrollStudentInCourse(student, course);
            Assert.fail("Should have thrown exception");
        } catch (CourseIsAtCapacityException ex) {

        }
    }


    /**
     * Scenario: Student wants to enroll in a course, but does not have the prerequisites
     *
     * Given a student with good credit And the course that they want to enroll in has openings And
     * the course has two pre-requisite classes And the student has not fulfilled any of the
     * prerequisites When that student enrolls in the course Then they should not be allowed to
     * enroll in the course
     */
    @Test
    public void testEnrollStudentInCourse_StudentDoesNotHavePrerequisites() {
        Course course = fixture.createAndPersistCourse();
        Course prereq1 = fixture.createAndPersistCourse();
        Course prereq2 = fixture.createAndPersistCourse();

        fixture.createAndPersistPrerequisitesForGiven(course, Arrays.asList(prereq1, prereq2));
        Student student = fixture.createAndPersistStudent();

        try {
            service.enrollStudentInCourse(student, course);
            Assert.fail("Should have thrown an exception");
        } catch (StudentDidNotCompletePrerequisitesException e) {

        }

    }

    /**
     * Scenario: Student wants to enroll in a course, but does not have the prerequisites
     *
     * Given a student with good credit And the course that they want to enroll in has openings And
     * the course has two pre-requisite classes And the student has only fulfilled one prerequisite
     * When that student enrolls in the course Then they should not be allowed to enroll in the
     * course
     */
    @Test
    public void testEnrollStudentInCourse_StudentCompletedOneOfTwoPrerequisites() {
        Course course = fixture.createAndPersistCourse();
        Course prereq1 = fixture.createAndPersistCourse();
        Course prereq2 = fixture.createAndPersistCourse();

        fixture.createAndPersistPrerequisitesForGiven(course, Arrays.asList(prereq1, prereq2));
        Student student = fixture.createAndPersistStudent();
        fixture.createAndPersistEnrollment(student, prereq1, true);

        try {
            service.enrollStudentInCourse(student, course);
            Assert.fail("Should have thrown an exception");
        } catch (StudentDidNotCompletePrerequisitesException e) {

        }

    }

    /**
     * Scenario: Happy Case
     *
     * Given a student with good credit And the course that they want to enroll in has openings And
     * the course has two pre-requisite classes And the student has fulfilled all prerequisites When
     * that student enrolls in the course Then they should be allowed to enroll in the course
     */
    @Test
    public void testEnrollStudentInCourse_HappyCase() {
        Course course = fixture.createAndPersistCourse();
        Course prereq1 = fixture.createAndPersistCourse();
        Course prereq2 = fixture.createAndPersistCourse();

        fixture.createAndPersistPrerequisitesForGiven(course, Arrays.asList(prereq1, prereq2));
        Student student = fixture.createAndPersistStudent();

        fixture.createAndPersistEnrollment(student, prereq1, true);
        fixture.createAndPersistEnrollment(student, prereq2, true);

        fixture.showRecords("enrollment");

        service.enrollStudentInCourse(student, course);

        fixture.showRecords("enrollment");

    }

    /* ============================================================================= */

}