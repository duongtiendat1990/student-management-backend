package com.sopen.studentmanagement.model;

import javax.persistence.*;

@Entity
public class Grade {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User student;

  @ManyToOne(targetEntity = Subject.class)
  @JoinColumn(name = "subject_id")
  private Subject subject;

  private Double score;

  public Grade() {
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
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
