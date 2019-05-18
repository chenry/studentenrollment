package com.beercitycode.tddaholic.studentenrollment.model;

import javax.persistence.*;

@Entity
public class CoursePrerequisite {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  private Long prereqCourseId;

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPrereqCourseId() {
    return prereqCourseId;
  }

  public void setPrereqCourseId(Long prereqCourseId) {
    this.prereqCourseId = prereqCourseId;
  }
}
