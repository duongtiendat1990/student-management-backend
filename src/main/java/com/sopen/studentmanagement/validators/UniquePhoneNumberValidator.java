package com.sopen.studentmanagement.validators;

import com.sopen.studentmanagement.services.UserService;
import com.sopen.studentmanagement.validators.annotation.UniquePhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.existsByPhoneNumber(value);
    }

}