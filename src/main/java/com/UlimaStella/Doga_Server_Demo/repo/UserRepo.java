package com.UlimaStella.Doga_Server_Demo.repo;

import com.UlimaStella.Doga_Server_Demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findUserById(Long userId);

}
