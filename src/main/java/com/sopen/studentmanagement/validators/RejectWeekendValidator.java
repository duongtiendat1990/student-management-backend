package com.sopen.studentmanagement.validators;

import com.sopen.studentmanagement.validators.annotation.RejectWeekend;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class RejectWeekendValidator implements ConstraintValidator<RejectWeekend, Calendar> {
  @Override
  public void initialize(RejectWeekend constraintAnnotation) {

  }

  @Override
  public boolean isValid(Calendar value, ConstraintValidatorContext context) {
    return value==null||value.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY;
  }
}
