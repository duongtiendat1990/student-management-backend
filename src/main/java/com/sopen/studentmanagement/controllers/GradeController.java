package com.sopen.studentmanagement.controllers;

import com.sopen.studentmanagement.model.Grade;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.services.GradeService;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/grades")
public class GradeController {
  @Autowired
  GradeService gradeService;

  @Autowired
  UserService userService;

  @GetMapping("/student:{studentId}/subject:{subjectId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Grade> getByStudentAndSubject(@PathVariable Long studentId, @PathVariable Long subjectId){
    Grade grade = gradeService.findByStudentIdAndSubjectId(studentId,subjectId);
    return new ResponseEntity<>(grade, HttpStatus.OK);
  }
  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> saveGrades(@RequestBody List<Grade> grades){
    grades.forEach(grade -> {
      gradeService.save(grade);
    });
    return new ResponseEntity(HttpStatus.OK);
  }
  @GetMapping
  @PreAuthorize("hasRole('ROLE_STUDENT')")
  public ResponseEntity<List<Grade>> getAllByStudent(){
    User student = userService.getUserByAuth();
    return new ResponseEntity<>(gradeService.findAllByStudent(student),HttpStatus.OK);
  }
}
