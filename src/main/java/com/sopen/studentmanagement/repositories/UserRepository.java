package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.User;

public interface UserRepository{
  User findById(Long id);
}
