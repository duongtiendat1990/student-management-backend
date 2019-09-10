package com.sopen.studentmanagement.services;

import com.sopen.studentmanagement.model.User;

import java.util.List;

public interface UserService {
  User findById(Long id);
  List<User> findAllStudent();
  User findByEmailIgnoreCase(String email);
  void save(User user);
  boolean existsByUsername (String username);
  boolean existsByEmail (String email);
  boolean existsByPhoneNumber (String phoneNumber);

}
