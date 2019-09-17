package com.sopen.studentmanagement.validators;

import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.validators.annotation.AppropriateStartPeriod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AppropriateStartPeriodValidator implements ConstraintValidator<AppropriateStartPeriod, Class> {
  @Override
  public void initialize(AppropriateStartPeriod constraintAnnotation) {

  }

  @Override
  public boolean isValid(Class value, ConstraintValidatorContext context) {
    return value.getStartPeriod()+value.getSubject().getCredits()-1<=6;
  }
}
