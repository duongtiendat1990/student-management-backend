package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.NoResultException;
import java.util.List;

public interface UserRepository extends Repository<User>{
  User findByUserName(String username) throws UsernameNotFoundException;
  User findByEmail(String email);
  User findByPhoneNumber(String phoneNumber);
  User findByEmailIgnoreCase(String email);
  List<User> findAllStudent();
  List<User> findAllStudentBySubjectId(Long subjectId);
  List<User> findAllStudentByClassId(Long classId);

  boolean existsByUsername (String username);
  boolean existsByEmail (String email);
  boolean existsByPhoneNumber (String phoneNumber);
}
