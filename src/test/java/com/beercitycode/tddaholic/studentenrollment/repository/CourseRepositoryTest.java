package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.fixtures.Fixture;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.CoursePrerequisite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

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
    }

    @Test
    public void testFindById_RecordFoundMultiplePrerequisites() {
        Assert.assertNull("");
    }


}