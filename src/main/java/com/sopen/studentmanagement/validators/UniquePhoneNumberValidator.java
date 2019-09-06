package com.sopen.studentmanagement.validators;

import com.sopen.studentmanagement.services.UserService;
import com.sopen.studentmanagement.validators.annotation.UniquePhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.existsByPhoneNumber(value);
    }

}