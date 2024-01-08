package com.atu.hsms.services.user;

import com.atu.hsms.domain.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User getUser(String username);
    User registerUser(User user);
  }
