package com.beercitycode.tddaholic.studentenrollment.fixtures;

import com.beercitycode.tddaholic.studentenrollment.model.Student;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class StudentFixture {

    public static Student create(Long id) {
        Student student = new Student();

        student.setId(id);
        student.setLastName("LastName-" + id);
        student.setFirstName("FirstName-" + id);
        student.setCreditRating(100);

        return student;
    }

    public static void saveStudent(NamedParameterJdbcTemplate jdbcTemplate, Student student) {
        //@formatter:off
        String updateQuery =
            "insert into student " +
                "(ID, FIRST_NAME, LAST_NAME, " +
                "CREDIT_RATING) " +
                "values (:id, :firstName, :lastName, " +
                ":creditRating)";

        //@formatter:on

        BeanPropertySqlParameterSource bpsps = new BeanPropertySqlParameterSource(student);
        jdbcTemplate.update(updateQuery, bpsps);
    }
}
