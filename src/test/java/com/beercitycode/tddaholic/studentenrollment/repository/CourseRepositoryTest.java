package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.fixtures.Fixture;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private Fixture fixture;

    @Test
    public void testFindById_NoMatches() {
        Optional<Course> course = repository.findById(-100L);

        Assert.assertFalse(course.isPresent());
    }

    @Test
    public void testFindId_RecordFoundNoPrerequisites() {
        Long courseId = -100L;
        fixture.createAndPersistCourse(courseId);

        Optional<Course> course = repository.findById(courseId);
        Assert.assertTrue(course.isPresent());
        Assert.assertEquals(0, course.get().getCoursePrerequisiteList().size());
    }

    @Test
    public void testFindById_RecordFoundMultiplePrerequisites() {

        Course course1 = fixture.createAndPersistCourse(-100L);
        Course course2 = fixture.createAndPersistCourse(-200L);
        Course course3 = fixture.createAndPersistCourse(-300L);

        fixture.showRecords("course");

        fixture.createAndPersistPrerequisitesForGiven(course1, Lists.newArrayList(course2, course3));
        fixture.showRecords("course_prerequisite");

        Optional<Course> persistedCourse = repository.findById(course1.getId());


        Assert.assertEquals(2, persistedCourse.get().getCoursePrerequisiteList().size());
    }


}