package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.message.request.ChangePasswordForm;
import com.sopen.studentmanagement.message.response.ResponseMessage;
import com.sopen.studentmanagement.model.*;
import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.repositories.RoleRepository;
import com.sopen.studentmanagement.security.services.EmailSenderService;
import com.sopen.studentmanagement.services.ClassService;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  ClassService classService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private EmailSenderService emailSenderService;

  @GetMapping("/search/class:{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> findAllStudentByClass(@PathVariable Long id) {
    List<User> students = userService.findAllStudentByClassId(id);
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> getAllStudent() {
    List<User> students = userService.findAllStudent();
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> createStudent(@Valid @RequestBody User user) {
    Role role = roleRepository.findByName(RoleName.ROLE_STUDENT);
    Set<Role> roles = new HashSet<Role>();
    roles.add(role);
    user.setRoles(roles);
    user.setPassword(passwordEncoder.encode("123456"));
    Class[][] timetable = new Class[6][6];
    user.setTimetable(timetable);
    userService.save(user);
    emailSenderService.sendEmailCreateUser(user);
    return new ResponseEntity<>(new ResponseMessage("Please notify student to login your email to confirm"),
      HttpStatus.OK);
  }

  @PutMapping
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public ResponseEntity<?> enrollClass(@Valid @RequestBody Class aClass) {
    User student = userService.getUserByAuth();
    Set<Class> classes = student.getClasses();
    final Class finalClass = classService.findById(aClass.getId());
    for (Class aClass1 : classes) {
      if (finalClass.equals(aClass1)) {
        return new ResponseEntity<>(new ResponseMessage("You have already taken this class"), HttpStatus.BAD_REQUEST);
      }
      if ((finalClass.getStartTime().after(aClass1.getStartTime()) && finalClass.getStartTime().before(
        aClass1.getEndTime()) && finalClass.getSubject().equals(aClass1.getSubject()))) {
        return new ResponseEntity<>(
          new ResponseMessage("You have already taken class" + aClass1.getName() + "with same subject"),
          HttpStatus.CONFLICT);
      }
    }
    classes.add(aClass);
    student.setClasses(classes);
    Set<Subject> subjects = student.getSubjects();
    subjects.add(aClass.getSubject());
    student.setSubjects(subjects);
    userService.save(student);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/change-password")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm) {
    User user = userService.getUserByAuth();
    if (user != null) {
      if (passwordEncoder.matches(changePasswordForm.getOldPassword(), user.getPassword())) {
        user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Password changed successfully"), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ResponseMessage("Wrong old password"), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ResponseMessage("Please login to change password"), HttpStatus.FORBIDDEN);
  }
}
