package com.sopen.studentmanagement.model;

import com.sopen.studentmanagement.validators.annotation.UniqueSubjectCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String name;

  @Column(unique = true, nullable = false)
  @NotNull
  @UniqueSubjectCode
  private String code;

  @NotNull
  private Double scoreFactor;

  private String note;

  @OneToMany(cascade=CascadeType.ALL)
  @NotEmpty
  @JoinColumn(name = "subject_id")
  private Set<Class> classes;

  public Subject() {
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

  public Double getScoreFactor() {
    return scoreFactor;
  }

  public void setScoreFactor(Double scoreFactor) {
    this.scoreFactor = scoreFactor;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Set<Class> getClasses() {
    return classes;
  }

  public void setClasses(Set<Class> classes) {
    this.classes = classes;
  }
}
