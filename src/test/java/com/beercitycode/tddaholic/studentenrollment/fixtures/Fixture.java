package com.beercitycode.tddaholic.studentenrollment.fixtures;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.CoursePrerequisite;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class Fixture {
    private static final Logger logger = LoggerFactory.getLogger(Fixture.class);
    private static AtomicLong id = new AtomicLong(0);

    public static Long getNextId() {
        return id.incrementAndGet();
    }

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void showRecords(String tableName) {
        List<Map<String, Object>> records = jdbcTemplate.queryForList("select * from " + tableName, new HashMap<>());

        logger.info(String.format("[%s] record count: %d", tableName, records.size()));
        for (Map<String, Object> currRecord : records) {
            logger.info("\t" + currRecord);
        }
    }

    public Student createAndPersistStudent(Long id) {
        Student student = StudentFixture.create(id);
        StudentFixture.saveStudent(jdbcTemplate, student);
        return student;
    }

    public Course createAndPersistCourse(Long id) {
        Course course = CourseFixture.create(id);
        CourseFixture.saveCourse(jdbcTemplate, course);
        return course;
    }

    public void createAndPersistPrerequisitesForGiven(Course givenCourse, List<Course> prereqCourses) {

        List<CoursePrerequisite> coursePrerequisites = new ArrayList<CoursePrerequisite>();

        for (Course currPrereqCourse : prereqCourses) {
            CoursePrerequisite coursePrerequisite = CoursePrerequisiteFixture.create(getNextId(), givenCourse, currPrereqCourse);
            CoursePrerequisiteFixture.saveCoursePrerequisite(jdbcTemplate, coursePrerequisite);
        }


    }

    public Enrollment createAndPersistEnrollment(Long enrollmentId, Student student, Course course) {
        Enrollment enrollment = EnrollmentFixture.create(enrollmentId, student, course);
        EnrollmentFixture.saveEnrollment(jdbcTemplate, enrollment);
        return enrollment;
    }
}
