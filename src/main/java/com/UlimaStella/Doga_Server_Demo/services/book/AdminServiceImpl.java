package com.UlimaStella.Doga_Server_Demo.services.book;

import com.UlimaStella.Doga_Server_Demo.domain.Role;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.repo.RoleRepo;
import com.UlimaStella.Doga_Server_Demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {


        if (userRepo.findByUsername(user.getPhone()) != null) {
            log.info("This username is already using.");

            return null;
        } else {
            log.info("Saving new user to the database");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return user;
        }
    }

    @Override
    public User deleteUser(long id) {

        User user = userRepo.findUserById(id);
        if (user != null) {
            log.info("Deleting user (id : {}) from the database", id);
            userRepo.delete(user);
            return user;
        } else return null;
    }


    @Override
    public User updateUser(User user) {


        User userFromDb = userRepo.findUserById(user.getId());
        // crush the variables of the object found

        if (userFromDb != null) {
            log.info("Update user (id : {}) from the database", user.getId());
            userFromDb.setNameSurname(user.getNameSurname());
            userFromDb.setRoles(user.getRoles());
            userRepo.save(userFromDb);


            return userFromDb;
        } else return null;
    }

    @Override
    public Role saveRole(Role role) {

        log.info("Saving new {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        log.info("Adding {} to {} on the database", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }



    @Override
    public List<User> getUsers() {

        log.info("getting all user from the database");
        return userRepo.findAll();
    }


}
