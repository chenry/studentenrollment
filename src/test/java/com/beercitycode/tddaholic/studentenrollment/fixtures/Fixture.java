package com.beercitycode.tddaholic.studentenrollment.fixtures;

import com.beercitycode.tddaholic.studentenrollment.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Fixture {
    private static final Logger logger = LoggerFactory.getLogger(Fixture.class);

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

}
