package com.sopen.studentmanagement.validators;

import javax.validation.*;

import com.sopen.studentmanagement.services.UserService;
import com.sopen.studentmanagement.validators.annotation.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.existsByEmail(value);
    }

}
