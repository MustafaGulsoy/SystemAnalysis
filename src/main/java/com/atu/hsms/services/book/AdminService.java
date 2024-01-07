package com.atu.hsms.services.book;

import com.atu.hsms.domain.Role;
import com.atu.hsms.domain.User;

import java.util.List;

public interface AdminService {


    User saveUser(User user);
    User deleteUser(long id);

    User updateUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    List<User> getUsers();



}
