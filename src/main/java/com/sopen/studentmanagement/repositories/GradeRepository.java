package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;

import java.util.List;

public interface GradeRepository {
  Grade findByStudentIdAndSubjectIdAndClassId(Long studentId, Long subjectId, Long classId);
  List<Grade> findAllByStudent(User student);
  void save(Grade model);
}
