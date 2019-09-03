package com.sopen.studentmanagement.services.Impl;

import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.UserRepository;
import com.sopen.studentmanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;
  @Override
  public User findById(Long id) {
    return userRepository.findById(id);
  }
}
