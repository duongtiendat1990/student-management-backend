package com.sopen.studentmanagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sopen.studentmanagement.validators.annotation.UniqueEmail;
import com.sopen.studentmanagement.validators.annotation.UniquePhoneNumber;
import com.sopen.studentmanagement.validators.annotation.UniqueUsername;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.*;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String name;

  @Column(unique = true)
  @UniqueUsername
  @NaturalId
  private String username;

  @JsonIgnore
  private String password;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Past(message = "Birthday must be in the past")
  private Date birthday;

  private String hometown;

  @Column(unique = true)
  @Pattern(regexp = "0([0-9]{9,10})")
  @UniquePhoneNumber
  private String phoneNumber;

  @Column(nullable = false, unique = true)
  @Email
  @UniqueEmail
  private String email;

  @Lob
  private byte[] timetable;

  private boolean enabled;

  private String note;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "student_class",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "class_id"))
  @JsonIgnoreProperties(value = "subject", allowSetters = true)
  @JsonIdentityInfo ( scope = Class.class, generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
  private Set<Class> classes = new HashSet<>();

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "student_subject",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
  @JsonIgnoreProperties(value = "classes", allowSetters = true)
  @JsonIdentityInfo( scope = Subject.class,generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")
  private Set<Subject> subjects = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();


  public User() {
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getHometown() {
    return hometown;
  }

  public void setHometown(String hometown) {
    this.hometown = hometown;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Class[][] getTimetable() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(timetable,Class[][].class);
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void setTimetable(Class[][] timetable) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      this.timetable = mapper.writeValueAsBytes(timetable);
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
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

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}