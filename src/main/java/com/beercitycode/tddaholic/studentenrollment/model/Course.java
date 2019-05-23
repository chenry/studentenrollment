package com.beercitycode.tddaholic.studentenrollment.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseName;
    private String description;
    private Integer classSizeLimit;

    @OneToMany(mappedBy = "course")
    private List<CoursePrerequisite> coursePrerequisiteList;

    public List<CoursePrerequisite> getCoursePrerequisiteList() {
        return coursePrerequisiteList;
    }

    public void setCoursePrerequisiteList(List<CoursePrerequisite> coursePrerequisiteList) {
        this.coursePrerequisiteList = coursePrerequisiteList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClassSizeLimit() {
        return classSizeLimit;
    }

    public void setClassSizeLimit(Integer classSizeLimit) {
        this.classSizeLimit = classSizeLimit;
    }
}
