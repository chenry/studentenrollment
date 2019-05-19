package com.beercitycode.tddaholic.studentenrollment.model;

import javax.persistence.*;

@Entity
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne
  @JoinColumn(name = "course_id")
  private Course course;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
