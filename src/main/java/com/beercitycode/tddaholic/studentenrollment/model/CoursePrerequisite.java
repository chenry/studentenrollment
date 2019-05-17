package com.beercitycode.tddaholic.studentenrollment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CoursePrerequisite {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long courseId;
  private Long prereqCourseId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCourseId() {
    return courseId;
  }

  public void setCourseId(Long courseId) {
    this.courseId = courseId;
  }

  public Long getPrereqCourseId() {
    return prereqCourseId;
  }

  public void setPrereqCourseId(Long prereqCourseId) {
    this.prereqCourseId = prereqCourseId;
  }
}
