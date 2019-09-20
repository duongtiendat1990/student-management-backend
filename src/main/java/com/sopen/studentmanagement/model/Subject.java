package com.sopen.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sopen.studentmanagement.validators.annotation.UniqueSubjectCode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
//@JsonIdentityInfo(scope = Subject.class,
//  generator = ObjectIdGenerators.PropertyGenerator.class,
//  property = "id")
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

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

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "subject_id")
  @JsonIdentityInfo ( scope = Class.class, generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
  @JsonIgnoreProperties(value = "subject", allowSetters = true)
//  @Valid
  private Set<Class> classes;

  public Subject() {
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
