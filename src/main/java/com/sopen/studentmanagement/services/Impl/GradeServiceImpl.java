package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.GradeRepository;
import com.sopen.studentmanagement.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class GradeServiceImpl implements GradeService {
  @Autowired
  GradeRepository gradeRepository;

  @Override
  public Grade findByStudentIdAndSubjectIdAndClassId(Long studentId, Long subjectId, Long classId) {
    return gradeRepository.findByStudentIdAndSubjectIdAndClassId(studentId,subjectId, classId);
  }

  @Override
  public List<Grade> findAllByStudent(User student) {
    return gradeRepository.findAllByStudent(student);
  }

  @Override
  public Double getGPA(User student) {
    List<Grade> grades = findAllByStudent(student);
    AtomicReference<Long> credits = new AtomicReference<>(0L);
    AtomicReference<Double> totalScore = new AtomicReference<>(0D);
    grades.forEach(grade -> {
      credits.updateAndGet(v -> v + grade.getSubject().getCredits());
      totalScore.updateAndGet(v -> v + grade.getScore() * grade.getSubject().getCredits());
    });
    return totalScore.get() / credits.get();
  }

  @Override
  public void save(Grade grade) {
    gradeRepository.save(grade);
  }
}
