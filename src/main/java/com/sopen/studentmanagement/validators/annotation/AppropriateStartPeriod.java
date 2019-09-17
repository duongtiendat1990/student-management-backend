package com.sopen.studentmanagement.validators.annotation;

import com.sopen.studentmanagement.validators.RejectWeekendValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RejectWeekendValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD })
public @interface AppropriateStartPeriod {
  public String message() default "There must be enough period left for class!";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default{};
}
