package com.UlimaStella.Doga_Server_Demo.services.book;

import com.UlimaStella.Doga_Server_Demo.domain.Role;
import com.UlimaStella.Doga_Server_Demo.domain.User;

import java.util.List;

public interface AdminService {


    User saveUser(User user);
    User deleteUser(long id);

    User updateUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    List<User> getUsers();



}
