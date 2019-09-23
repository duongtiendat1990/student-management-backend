package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.Class;

import java.util.List;

public interface ClassRepository extends Repository<Class> {
    Class findByCode(String code);
    List<Class> findAllBySubjectId(Long subjectId);
}
