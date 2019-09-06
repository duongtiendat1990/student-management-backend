package com.sopen.studentmanagement.services;

import com.sopen.studentmanagement.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();

    Subject findById(Long id);

    void save(Subject subject);

    void remove(Long id);
}
