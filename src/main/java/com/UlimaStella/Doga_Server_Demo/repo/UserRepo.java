package com.UlimaStella.Doga_Server_Demo.repo;

import com.UlimaStella.Doga_Server_Demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
