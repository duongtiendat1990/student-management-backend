package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.UserRepository;
import com.sopen.studentmanagement.security.services.UserPrinciple;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;
  @Override
  public User findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public List<User> findAllStudent() {
    return userRepository.findAllStudent();
  }

  @Override
  public List<User> findAllStudentByClassId(Long classId) {
    return userRepository.findAllStudentByClassId(classId);
  }

  @Override
  public User findByEmailIgnoreCase(String email) {
    return userRepository.findByEmailIgnoreCase(email);
  }

  @Override
  public User getUserByAuth() {
    Object userPrinciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Long user_id = ((UserPrinciple) userPrinciple).getId();
    return findById(user_id);
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public boolean existsByPhoneNumber(String phoneNumber) {
    return userRepository.existsByPhoneNumber(phoneNumber);
  }
}
