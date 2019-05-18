package com.beercitycode.tddaholic.studentenrollment.repository;

import com.beercitycode.tddaholic.studentenrollment.fixtures.Fixture;
import com.beercitycode.tddaholic.studentenrollment.model.CoursePrerequisite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoursePrerequisiteRepositoryTest {
    @Autowired
    private CoursePrerequisiteRepository repository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private Fixture fixture;

    @Test
    public void testFindById_NoMatches() {
        Optional<CoursePrerequisite> coursePrerequisite = repository.findById(-100L);

        Assert.assertFalse(coursePrerequisite.isPresent());
    }

    @Test
    public void testFindId_RecordFound() {

    }

}