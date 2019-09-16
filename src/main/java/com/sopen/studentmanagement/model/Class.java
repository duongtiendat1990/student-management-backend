package com.sopen.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sopen.studentmanagement.validators.annotation.UniqueClassCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Class {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String name;

  @Column(unique = true)
  @NotNull
  @UniqueClassCode
  private String code;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Calendar startTime;

  private Calendar endTime;

  @NotNull
  private Integer startPeriod;

  private Integer endPeriod;

  private DayOfWeek day;

  private String note;

  @ManyToOne(fetch = FetchType.EAGER)
  @NotNull
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
  private Subject subject;

  public Class() {
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Calendar getStartTime() {
    return startTime;
  }

  public void setStartTime(Calendar startTime) { this.startTime = startTime; }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }
}
