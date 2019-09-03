package com.sopen.studentmanagement;


import com.sopen.studentmanagement.model.Role;
import com.sopen.studentmanagement.model.RoleName;
import com.sopen.studentmanagement.model.User;
import com.sopen.studentmanagement.repositories.RoleRepository;
import com.sopen.studentmanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static jdk.nashorn.internal.runtime.Context.printStackTrace;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String...args) throws Exception {
    if (roleRepository.findByName(RoleName.ROLE_ADMIN)==null){
      roleRepository.save(new Role(RoleName.ROLE_ADMIN));
    }

    if (roleRepository.findByName(RoleName.ROLE_STUDENT)==null){
      roleRepository.save(new Role(RoleName.ROLE_STUDENT));
    }

    try {
      userRepository.findByUserName("admin");
    } catch (UsernameNotFoundException e){
      User admin = new User();
      admin.setUsername("admin");
      admin.setPassword(passwordEncoder.encode("password"));
      admin.setEnabled(true);
      admin.setEmail("duongtiendat1990@gmail.com");
      admin.setPhoneNumber("0389917146");
      Set<Role> roles = new HashSet<Role>();
      roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN));
      admin.setRoles(roles);
      userRepository.save(admin);
    }
  }
}