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
import java.time.DayOfWeek;
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


  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> getAllStudent(@RequestParam(required = false) Long classId) {
    List<User> students;
    if (classId != null) {
      students = userService.findAllStudentByClassId(classId);
    }
    else {
      students = userService.findAllStudent();
    }
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
  public ResponseEntity<?> enrollClass(@RequestBody Class aClass) {
    try {
      userService.checkConflict(aClass);
      userService.enrollClass(aClass);
      return new ResponseEntity<>(new ResponseMessage("Enroll class " + aClass.getName() + " success fully"),HttpStatus.OK);
    }
    catch (ResponseMessage message) {
      return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
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
  @GetMapping("/timetable")
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public ResponseEntity<?> getTimetable(){
    User student = userService.getUserByAuth();
    return new ResponseEntity<>(student.getTimetable(), HttpStatus.OK);
  }
}
