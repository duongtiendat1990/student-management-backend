package com.sopen.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
  @JsonIgnoreProperties(value = {"classes","subjects","timetable"})
  private User student;


  @ManyToOne(targetEntity = Subject.class)
  @JoinColumn(name = "subject_id")
//  @JsonIdentityInfo( scope = Subject.class,generator = ObjectIdGenerators.PropertyGenerator.class,
//    property = "id")
  @JsonIgnoreProperties(value = "classes")
  private Subject subject;

  @ManyToOne(targetEntity = Class.class)
  @JoinColumn(name = "class_id")
//  @JsonIdentityInfo(scope = Class.class, generator = ObjectIdGenerators.PropertyGenerator.class,
//    property = "id")
  @JsonIgnoreProperties(value = "subject")
  private Class klass;

  public Class getKlass() {
    return klass;
  }

  public void setKlass(Class klass) {
    this.klass = klass;
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
