package com.sopen.studentmanagement.services;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;

import java.util.List;

public interface GradeService {
  Grade findByStudentIdAndSubjectId(Long studentId, Long subjectId);
  List<Grade> findAllByStudent(User student);
  void save(Grade model);
}
