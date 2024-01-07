package com.atu.hsms.repo;

import com.atu.hsms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findUserById(Long userId);

}
