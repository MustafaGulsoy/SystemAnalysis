package com.UlimaStella.Doga_Server_Demo.controller;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.models.RoleToUser;
import com.UlimaStella.Doga_Server_Demo.services.book.AdminService;
import com.UlimaStella.Doga_Server_Demo.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;
    private final String path = "/api/admin";

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = new ArrayList<>();
        for (User user : adminService.getUsers()) {
            users.add(user);
            users.get(users.size() - 1).setPassword(null);


        }
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/saveUser").toUriString());
        User newUser = adminService.saveUser(user);
        if (newUser == null)

            return ResponseEntity.ok().body(new User(null,"This username is already registered","","",new ArrayList<>(),new ArrayList<>()));
        else
            return ResponseEntity.created(uri).body(newUser);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/deleteUser").toUriString());

        User newUser = adminService.deleteUser(user.getId());
        if (newUser == null)

            return ResponseEntity.ok().body(new User(null,"This username is NOT used by any user on database","","",new ArrayList<>(),new ArrayList<>()));
        else
            return ResponseEntity.created(uri).body(newUser);

    }
    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/deleteUser").toUriString());

        User newUser = adminService.updateUser(user);
        if (newUser == null)

            return ResponseEntity.ok().body(new User(null,"User NOT found any user on database","","",new ArrayList<>(),new ArrayList<>()));
        else
            return ResponseEntity.created(uri).body(newUser);

    }

    @GetMapping("/addBook")
    public ResponseEntity<Book> addBook(Book book) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/addBook").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveBook(book));

    }

    @GetMapping("/removeBook")
    public ResponseEntity<Book> removeBook(long book) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/deleteUser").toUriString());
        return ResponseEntity.created(uri).body(adminService.deleteBook(book));
    }

    @GetMapping("/updateBook")
    public ResponseEntity<Book> updateBook(Book book) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/deleteUser").toUriString());
        return ResponseEntity.created(uri).body(adminService.updateBook(book));
    }

    @PostMapping("/role/save")
    public ResponseEntity<User> saveRole(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/role/save").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveUser(user));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUser form) {
        adminService.addRoleToUser(form.getUsername(), form.getUsername());
        return ResponseEntity.ok().build();
    }


}
