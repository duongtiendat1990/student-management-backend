package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.Subject;

public interface SubjectRepository extends Repository<Subject> {
    Subject findByCode(String code);
}
