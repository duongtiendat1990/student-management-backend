package com.sopen.studentmanagement.repositories;

import com.sopen.studentmanagement.model.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository {
  ConfirmationToken findByConfirmationToken(String confirmationToken);
  void save(ConfirmationToken confirmationToken);
}
