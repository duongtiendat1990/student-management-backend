package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    User user = userService.findById(id);
    return new ResponseEntity < User > (user,HttpStatus.OK);
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<User>> getAllStudent(){
    return null;
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> createStudent(@RequestBody User user){
    return null;
  }

}
