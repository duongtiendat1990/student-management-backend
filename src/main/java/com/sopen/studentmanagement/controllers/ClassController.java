package com.sopen.studentmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sopen.studentmanagement.model.Class;
import com.sopen.studentmanagement.model.Subject;
import com.sopen.studentmanagement.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/classes")
public class ClassController {
  @Autowired
  ClassService classService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STUDENT')")
  public ResponseEntity<List<Class>> getAllClass(@RequestParam(required = false) Long subjectId){
    if (subjectId!=null){
      return new ResponseEntity<>(classService.findAllBySubjectId(subjectId),HttpStatus.OK);
    }
    return new ResponseEntity<>(classService.findAll(), HttpStatus.OK);
  }
}
