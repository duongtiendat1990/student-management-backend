package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.Class;

public interface ClassRepository extends Repository<Class> {
    Class findByCode(String code);
}
