package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.NoResultException;

public interface UserRepository extends Repository<User>{
  User findByUserName(String username) throws UsernameNotFoundException;
}
