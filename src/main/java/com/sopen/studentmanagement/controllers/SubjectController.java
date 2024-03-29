package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.message.response.ResponseMessage;
import com.sopen.studentmanagement.model.Subject;
import com.sopen.studentmanagement.services.SubjectService;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/subjects")
public class SubjectController {
  @Autowired
  SubjectService subjectService;

  @Autowired
  UserService userService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> createSubject(@Valid @RequestBody Subject subject) {
    subjectService.save(subject);
    return new ResponseEntity<>(new ResponseMessage("Subject has been created successfully"), HttpStatus.OK);
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN')")
  public ResponseEntity<List<Subject>> getAllSubject() {
    return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN')")
  public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
    return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity updateSubject(@PathVariable Long id, @Valid @RequestBody Subject subject) {
    Long subjectId = subject.getId();
    if (id.equals(subjectId)) {
      if (userService.findAllStudentBySubjectId(subjectId).size() == 0) {
        subjectService.save(subject);
        return new ResponseEntity<>(new ResponseMessage("Update subject successfully"), HttpStatus.OK);
      }
      return new ResponseEntity<>(new ResponseMessage("There are students   enrolled this subject"), HttpStatus.FORBIDDEN);
    }
    else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }
}
