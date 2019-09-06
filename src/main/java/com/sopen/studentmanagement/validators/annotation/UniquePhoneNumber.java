package com.sopen.studentmanagement.validators.annotation;

import com.sopen.studentmanagement.validators.UniquePhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniquePhoneNumber {
    public String message() default "There is already user with this phone number!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
