package com.atu.hsms.repo;

import com.atu.hsms.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role,Long> {

    Role findByName(String name);


}
