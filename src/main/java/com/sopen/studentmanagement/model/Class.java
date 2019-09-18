package com.sopen.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sopen.studentmanagement.validators.annotation.AppropriateStartPeriod;
import com.sopen.studentmanagement.validators.annotation.RejectWeekend;
import com.sopen.studentmanagement.validators.annotation.UniqueClassCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@JsonIdentityInfo(scope = Class.class, generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id")
public class Class {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @Column(unique = true)
  @NotNull
  @UniqueClassCode
  @NaturalId
  private String code;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @RejectWeekend
  private Calendar startTime;

  private Calendar endTime;

  @NotNull
  @Range(min = 1, max = 6, message = "Start period must be within 1 and 6")
  @AppropriateStartPeriod
  private Integer startPeriod;

  private Integer endPeriod;

  private String note;

  @ManyToOne(fetch = FetchType.EAGER)
  @NotNull
//  @JsonIdentityInfo(scope = Subject.class, generator = ObjectIdGenerators.PropertyGenerator.class,
//    property = "id")
  private Subject subject;

  public Class() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public void setStartTime(Calendar startTime) {
    this.startTime = startTime;
  }

  public Calendar getEndTime() {
    return endTime;
  }

  public void setEndTime(Calendar endTime) {
    this.endTime = endTime;
  }

  public Integer getStartPeriod() {
    return startPeriod;
  }

  public void setStartPeriod(Integer startPeriod) {
    this.startPeriod = startPeriod;
  }

  public Integer getEndPeriod() {
    return endPeriod;
  }

  public void setEndPeriod(Integer endPeriod) {
    this.endPeriod = endPeriod;
  }

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
