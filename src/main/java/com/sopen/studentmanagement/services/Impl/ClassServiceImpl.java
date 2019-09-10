package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.repositories.ClassRepository;
import com.sopen.studentmanagement.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassRepository classRepository;

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Class findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public void save(Class model) {
        classRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        classRepository.remove(id);
    }

    @Override
    public Class findByCode(String code) {
        return classRepository.findByCode(code);
    }

    @Override
    public boolean existedByCode(String code) {
        return classRepository.findByCode(code)!=null;
    }
}
