package com.beercitycode.tddaholic.studentenrollment.fixtures;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.CoursePrerequisite;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class CoursePrerequisiteFixture {

    public static CoursePrerequisite create(Long id, Course course, Course prereqCourse) {
        CoursePrerequisite cp =  new CoursePrerequisite();
        cp.setId(id);
        cp.setCourse(course);
        cp.setPrereqCourse(prereqCourse);
        return cp;
    }

    public static void saveCoursePrerequisite(NamedParameterJdbcTemplate jdbcTemplate, CoursePrerequisite cp) {
        //@formatter:off
        String updateQuery =
                "insert into course_prerequisite " +
                        "(ID, COURSE_ID, PREREQ_COURSE_ID) " +
                        "values (:id, :courseId, :prereqCourseId)";

        //@formatter:on
        Map<String, Object> map = new HashMap<>();
        map.put("id", cp.getId());
        map.put("courseId", cp.getCourse().getId());
        map.put("prereqCourseId", cp.getPrereqCourse().getId());
        jdbcTemplate.update(updateQuery, map);

    }
}
