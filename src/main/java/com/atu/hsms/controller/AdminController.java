package com.atu.hsms.controller;

import com.atu.hsms.domain.User;
import com.atu.hsms.models.RoleToUser;
import com.atu.hsms.services.book.AdminService;
import com.atu.hsms.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    private final String path = "/api/admin";

    @GetMapping("/users/")
    public ResponseEntity<List<User>> getUsers(@RequestParam String department ) {

        List<User> users = new ArrayList<>();
        for (User user : adminService.getUsers()) {
            if(user.getDepartment().equals(department)) {
                users.add(user);
                users.get(users.size() - 1).setPassword(null);
            }

        }
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/saveUser").toUriString());
        User newUser = adminService.saveUser(user);
        if (newUser == null)

            return ResponseEntity.ok().body(new User(
                    null,"This username is already registered","","","","","",
                    "","","",new Date(),new Date(), new ArrayList<>()));
        else
            return ResponseEntity.created(uri).body(newUser);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {

        User deletedUser = adminService.deleteUser(user.getId());
        if (deletedUser == null)

            return ResponseEntity.ok().body(new User(
                    null,"This username is already registered","","","","","",
                    "","","",new Date(),new Date(), new ArrayList<>()));
        else {
            deletedUser.setPassword(null);
            return ResponseEntity.ok().body(deletedUser);
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        User newUser = adminService.updateUser(user);
        if (newUser == null)

            return ResponseEntity.ok().body(new User(
                    null,"This username is already registered","","","","","",
                    "","","",new Date(),new Date(), new ArrayList<>()));    else {
            newUser.setPassword(null);
            return ResponseEntity.ok().body(newUser);
        }
    }






    @PostMapping("/role/save")
    public ResponseEntity<User> saveRole(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/role/save").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveUser(user));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser form) {
        adminService.addRoleToUser(form.getUsername(), form.getUsername());
        return ResponseEntity.ok().build();
    }


}
