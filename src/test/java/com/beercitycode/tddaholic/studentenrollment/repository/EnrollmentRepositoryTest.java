package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.fixtures.Fixture;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private Fixture fixture;

    @Test
    public void testFindById_NotFound() {
        Optional<Enrollment> enrollment = repository.findById(-100L);

        Assert.assertFalse(enrollment.isPresent());
    }

    @Test
    public void testFindById_Found() {
        Long enrollmentId = -100L;

        Student student = fixture.createAndPersistStudent(Fixture.getNextId());
        Course course = fixture.createAndPersistCourse(Fixture.getNextId());

        fixture.createAndPersistEnrollment(enrollmentId, student, course);
        Optional<Enrollment> persistedEnrollment = repository.findById(enrollmentId);

        Assert.assertTrue(persistedEnrollment.isPresent());
        Assert.assertEquals(student.getId(), persistedEnrollment.get().getStudent().getId());
        Assert.assertEquals(course.getId(), persistedEnrollment.get().getCourse().getId());
    }



}