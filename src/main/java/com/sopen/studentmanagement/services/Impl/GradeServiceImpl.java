package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.GradeRepository;
import com.sopen.studentmanagement.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
  @Autowired
  GradeRepository gradeRepository;

  @Override
  public Grade findByStudentIdAndSubjectId(Long studentId, Long subjectId) {
    return gradeRepository.findByStudentIdAndSubjectId(studentId,subjectId);
  }

  @Override
  public List<Grade> findAllByStudent(User student) {
    return gradeRepository.findAllByStudent(student);
  }

  @Override
  public void save(Grade grade) {
    gradeRepository.save(grade);
  }
}
