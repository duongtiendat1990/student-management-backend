package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.model.Subject;
import com.sopen.studentmanagement.repositories.SubjectRepository;
import com.sopen.studentmanagement.services.ClassService;
import com.sopen.studentmanagement.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ClassService classService;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public void save(Subject subject) {
        for (Class aClass: subject.getClasses()) {
            aClass.setSubject(subject);
        }
        subjectRepository.save(subject);
    }

    @Override
    public void remove(Long id) {
        subjectRepository.remove(id);
    }

    @Override
    public Subject findByCode(String code) {
        return subjectRepository.findByCode(code);
    }

    @Override
    public boolean existedByCode(String code) {
        return subjectRepository.findByCode(code) != null;
    }
}
