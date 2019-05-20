package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.exception.CourseIsAtCapacityException;
import com.beercitycode.tddaholic.studentenrollment.exception.CourseNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentHasBadCreditException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.model.Student;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentEnrollmentService {

  @Autowired
  private StudentService studentService;

  @Autowired
  private CourseService courseService;

  @Autowired
  private EnrollmentService enrollmentService;

  public void enrollStudentInCourse(Student student, Course course) {
    Optional<Student> persistedStudent = studentService.findStudent(student.getId());

    if (!persistedStudent.isPresent()) {
      throw new StudentNotFoundException();
    }

    if (!studentService.hasGoodCredit(persistedStudent.get())) {
      throw new StudentHasBadCreditException();
    }

    if (isCourseFull(course)) {
      throw new CourseIsAtCapacityException();
    }


  }

  private boolean isCourseFull(Course course) {
    Optional<Course> persistedCourse = courseService.findCourseById(course.getId());

    if (!persistedCourse.isPresent()) {
      throw new CourseNotFoundException();
    }

    List<Enrollment> enrollmentList = enrollmentService.findEnrollmentsByCourseId(persistedCourse.get().getId());

    return enrollmentList.size() >= persistedCourse.get().getClassSizeLimit();
  }

}
