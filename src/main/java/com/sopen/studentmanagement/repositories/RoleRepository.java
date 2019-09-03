package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.Role;
import com.sopen.studentmanagement.model.RoleName;

public interface RoleRepository {
  Role findByName(RoleName roleName);
  void save(Role role);
}
