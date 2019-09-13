package com.sopen.studentmanagement.services;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;

import java.util.List;

public interface GradeService {
  Grade findByStudentIdAndSubjectIdAndClassId(Long studentId, Long subjectId, Long classId);
  List<Grade> findAllByStudent(User student);
  Double getGPA(User student);
  void save(Grade model);
}
