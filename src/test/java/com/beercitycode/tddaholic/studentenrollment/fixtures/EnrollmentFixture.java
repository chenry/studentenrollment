package com.beercitycode.tddaholic.studentenrollment.fixtures;

import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EnrollmentFixture {

    public static Enrollment create(Long id, Student student, Course course) {
        return create(id, student, course, false);
    }

    public static Enrollment create(Long id, Student student, Course course, boolean isCompleted) {
        Enrollment enrollment = new Enrollment();

        enrollment.setId(id);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setCompleted(isCompleted);
        return enrollment;
    }

    public static void saveEnrollment(NamedParameterJdbcTemplate jdbcTemplate,
        Enrollment enrollment) {
        //@formatter:off
        String updateQuery =
            "insert into enrollment " +
                "(ID, STUDENT_ID, COURSE_ID, "
                + "IS_COMPLETED) " +
                "values (:id, :studentId, :courseId, :isCompleted)";

        //@formatter:on
        Map<String, Object> map = new HashMap<>();
        map.put("id", enrollment.getId());
        map.put("studentId", enrollment.getStudent().getId());
        map.put("courseId", enrollment.getCourse().getId());
        map.put("isCompleted", enrollment.getCompleted());

        jdbcTemplate.update(updateQuery, map);

    }
}
