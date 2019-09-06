package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.model.Role;
import com.sopen.studentmanagement.model.RoleName;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.RoleRepository;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    return new ResponseEntity < User > (user,HttpStatus.OK);
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> getAllStudent(){
      List<User> students = userService.findAllStudent();
      return new ResponseEntity<List<User>>(students, HttpStatus.OK);
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> createStudent(@Valid @RequestBody User user){
    Role role = roleRepository.findByName(RoleName.ROLE_STUDENT);
    Set<Role> roles = new HashSet<Role>();
    roles.add(role);
    user.setRoles(roles);
    user.setPassword(passwordEncoder.encode("123456"));
    userService.save(user);
    return null;
  }

}
