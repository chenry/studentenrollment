package com.beercitycode.tddaholic.studentenrollment.fixtures;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CourseFixture {

    public static Course create(Long id) {

        Course course = new Course();

        course.setId(id);
        course.setCourseName("CourseName-" + id);
        course.setDescription("CourseDesc-" + id);
        course.setClassSizeLimit(10);

        return course;
    }

    public static void saveCourse(NamedParameterJdbcTemplate jdbcTemplate, Course course) {
        //@formatter:off
        String updateQuery =
            "insert into course " +
                "(id, course_name, description, " +
                "class_size_limit) " +
                "values " +
                "(:id, :courseName, :description, " +
                ":classSizeLimit)";
        //@formatter:on

        BeanPropertySqlParameterSource bpsps = new BeanPropertySqlParameterSource(course);
        jdbcTemplate.update(updateQuery, bpsps);

    }
}
