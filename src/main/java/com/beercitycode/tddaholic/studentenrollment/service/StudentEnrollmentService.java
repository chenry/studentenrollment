package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.exception.StudentNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEnrollmentService {

  @Autowired
  private StudentService studentService;

  public void enrollStudentInCourse(Student student, Course course) {
    Optional<Student> persistedStudent = studentService.findStudent(student.getId());

    if (!persistedStudent.isPresent()) {
      throw new StudentNotFoundException();
    }
  }
}
