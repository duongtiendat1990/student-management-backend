package com.sopen.studentmanagement.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Grade {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User student;

  @ManyToOne(targetEntity = Subject.class)
  @JoinColumn(name = "subject_id")
  private Subject subject;

  @ManyToOne(targetEntity = Class.class)
  @JoinColumn(name = "class_id")
  private Class aClass;

  public Class getAClass() {
    return aClass;
  }

  public void setAClass(Class aClass) {
    this.aClass = aClass;
  }

  @NotNull
  @Range(min = 0, max = 10, message = "Score must be within 0 and 10")
  private Double score;

  public Grade() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getStudent() {
    return student;
  }

  public void setStudent(User student) {
    this.student = student;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }
}
