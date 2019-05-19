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

  @ManyToOne
  @JoinColumn(name = "PREREQ_COURSE_ID")
  private Course prereqCourse;

  public Course getPrereqCourse() {
    return prereqCourse;
  }

  public void setPrereqCourse(Course prereqCourse) {
    this.prereqCourse = prereqCourse;
  }

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

}
