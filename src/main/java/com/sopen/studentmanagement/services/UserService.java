package com.sopen.studentmanagement.services;

import com.sopen.studentmanagement.model.User;

public interface UserService {
  User findById(Long id);
}
