package com.beercitycode.tddaholic.studentenrollment.service;

import com.beercitycode.tddaholic.studentenrollment.model.Student;
import com.beercitycode.tddaholic.studentenrollment.repository.StudentRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;


  public Optional<Student> findStudent(Long id) {
    return studentRepository.findById(id);
  }
}
