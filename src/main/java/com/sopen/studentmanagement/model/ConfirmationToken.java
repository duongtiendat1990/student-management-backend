package com.sopen.studentmanagement.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class ConfirmationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "token_id")
  private Long id;

  @Column(name = "confirmation_token")
  private String confirmationToken;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  public ConfirmationToken() {
  }

  public ConfirmationToken(User user) {
    this.user = user;
    createdDate = new Date();
    confirmationToken = UUID.randomUUID().toString();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getConfirmationToken() {
    return confirmationToken;
  }

  public void setConfirmationToken(String confirmationToken) {
    this.confirmationToken = confirmationToken;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}