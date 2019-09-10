package com.sopen.studentmanagement.validators;

import com.sopen.studentmanagement.services.SubjectService;
import com.sopen.studentmanagement.validators.annotation.UniqueSubjectCode;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSubjectCodeValidator implements ConstraintValidator<UniqueSubjectCode, String> {
    @Autowired
    SubjectService subjectService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value!=null && !subjectService.existedByCode(value);
    }
}
