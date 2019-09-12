package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/classes")
public class ClassController {
  @Autowired
  ClassService classService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<List<Class>> getAllClass(){
    return new ResponseEntity<>(classService.findAll(), HttpStatus.OK);
  }

}
