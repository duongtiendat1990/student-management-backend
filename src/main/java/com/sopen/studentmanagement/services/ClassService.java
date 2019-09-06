package com.sopen.studentmanagement.services;

import com.sopen.studentmanagement.model.Class;

import java.util.List;

public interface ClassService {
    List<Class> findAll();

    Class findById(Long id);

    void save(Class model);

    void remove(Long id);

    Class findByCode(String code);
}
