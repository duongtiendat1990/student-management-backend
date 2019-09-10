package com.sopen.studentmanagement.validators.annotation;

import com.sopen.studentmanagement.validators.UniqueClassCodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueClassCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueClassCode {
    public String message() default "There is already class with this code!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
