package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.exception.CourseIsAtCapacityException;
import com.beercitycode.tddaholic.studentenrollment.exception.CourseNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentDidNotCompletePrerequisitesException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentHasBadCreditException;
import com.beercitycode.tddaholic.studentenrollment.exception.StudentNotFoundException;
import com.beercitycode.tddaholic.studentenrollment.model.Course;
import com.beercitycode.tddaholic.studentenrollment.model.Enrollment;
import com.beercitycode.tddaholic.studentenrollment.model.Student;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class StudentEnrollmentService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    public void enrollStudentInCourse(Student student, Course course) {
        Optional<Student> optionalPersistedStudent = studentService.findStudent(student.getId());

        if (!optionalPersistedStudent.isPresent()) {
            throw new StudentNotFoundException();
        }

        Student persistedStudent = optionalPersistedStudent.get();

        if (!studentService.hasGoodCredit(persistedStudent)) {
            throw new StudentHasBadCreditException();
        }

        Optional<Course> optionalPersistedCourse = courseService.findCourseById(course.getId());
        if (!optionalPersistedCourse.isPresent()) {
            throw new CourseNotFoundException();
        }

        Course persistedCourse = optionalPersistedCourse.get();

        if (isCourseFull(persistedCourse)) {
            throw new CourseIsAtCapacityException();
        }

        if (!hasStudentCompletedCoursePrerequisites(persistedStudent, persistedCourse)) {
            throw new StudentDidNotCompletePrerequisitesException();
        }

        enrollmentService.enrollStudentInCourse(persistedStudent, persistedCourse);

    }

    private boolean hasStudentCompletedCoursePrerequisites(Student student, Course course) {

        List<Course> prerequisites = courseService.findCoursePrerequisites(course);

        if (CollectionUtils.isEmpty(prerequisites)) {
            return true;
        }

        Set<Long> studentCompletedCourseIds = enrollmentService
            .findCompletedCoursesByStudentId(student.getId());
        if (CollectionUtils.isEmpty(studentCompletedCourseIds)) {
            return false;
        }

        boolean foundMissingPrereq = false;
        for (Course currPrereqCourse : prerequisites) {
            if (!studentCompletedCourseIds.contains(currPrereqCourse.getId())) {
                foundMissingPrereq = true;
            }
        }

        if (foundMissingPrereq) {
            return false;
        }

        return true;
    }

    private boolean isCourseFull(Course persistedCourse) {
        List<Enrollment> enrollmentList = enrollmentService
            .findEnrollmentsByCourseId(persistedCourse.getId());

        return enrollmentList.size() >= persistedCourse.getClassSizeLimit();
    }

}
