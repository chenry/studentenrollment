package com.beercitycode.tddaholic.studentenrollment.model;

import javax.persistence.*;

@Entity
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_seq_generator")
  @SequenceGenerator(name="enrollment_seq_generator", sequenceName = "enrollment_seq")
  private Long id;

  @OneToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne
  @JoinColumn(name = "course_id")
  private Course course;

  private Boolean isCompleted;

  public Boolean getCompleted() {
    return isCompleted;
  }

  public void setCompleted(Boolean completed) {
    isCompleted = completed;
  }

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
