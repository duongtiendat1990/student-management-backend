package com.sopen.studentmanagement.validators;

import com.sopen.studentmanagement.services.ClassService;
import com.sopen.studentmanagement.validators.annotation.UniqueClassCode;
import com.sopen.studentmanagement.validators.annotation.UniqueSubjectCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class UniqueClassCodeValidator implements ConstraintValidator<UniqueClassCode, String> {
    @Autowired
    ClassService classService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value!=null && !classService.existedByCode(value);
    }
}
