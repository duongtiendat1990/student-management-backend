package com.sopen.studentmanagement.message.response;

import com.sopen.studentmanagement.model.Grade;

import java.util.List;

public class GradesMessage {
  private List<Grade> grades;
  private Double gpa;

  public GradesMessage() {
  }

  public GradesMessage(List<Grade> grades, Double gpa) {
    this.grades = grades;
    this.gpa = gpa;
  }

  public List<Grade> getGrades() {
    return grades;
  }

  public void setGrades(List<Grade> grades) {
    this.grades = grades;
  }

  public Double getGpa() {
    return gpa;
  }

  public void setGpa(Double gpa) {
    this.gpa = gpa;
  }
}
