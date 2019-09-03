package com.sopen.studentmanagement.model;

import javax.persistence.*;

@Entity
public class Grade {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User user;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
