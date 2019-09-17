package com.sopen.studentmanagement.model;

import com.sopen.studentmanagement.validators.annotation.UniqueSubjectCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
  @NaturalId
  private String code;

  @NotNull
  @Range(min = 1, max = 6, message = "Credits must be within 1 and 6")
  private Long credits;

  private String note;

  @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
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

  public Long getCredits() {
    return credits;
  }

  public void setCredits(Long credits) {
    this.credits = credits;
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
