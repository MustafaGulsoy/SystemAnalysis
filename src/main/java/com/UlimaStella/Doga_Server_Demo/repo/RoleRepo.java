package com.UlimaStella.Doga_Server_Demo.repo;

import com.UlimaStella.Doga_Server_Demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role,Long> {

    Role findByName(String name);


}
