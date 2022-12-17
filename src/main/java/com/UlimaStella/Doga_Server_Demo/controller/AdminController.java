package com.UlimaStella.Doga_Server_Demo.controller;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.domain.Writer;
import com.UlimaStella.Doga_Server_Demo.models.BookToWriter;
import com.UlimaStella.Doga_Server_Demo.models.RoleToUser;
import com.UlimaStella.Doga_Server_Demo.repo.WriterRepo;
import com.UlimaStella.Doga_Server_Demo.services.book.AdminService;
import com.UlimaStella.Doga_Server_Demo.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.sql.Time;
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

            return ResponseEntity.ok().body(new User(null, "This username is already registered", "", "", new ArrayList<>(), new ArrayList<>()));
        else
            return ResponseEntity.created(uri).body(newUser);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {

        User deletedUser = adminService.deleteUser(user.getId());
        if (deletedUser == null)

            return ResponseEntity.ok().body(new User(null, "This username is NOT used by any user on database", "", "", new ArrayList<>(), new ArrayList<>()));
        else {
            deletedUser.setPassword(null);
            return ResponseEntity.ok().body(deletedUser);
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        User newUser = adminService.updateUser(user);
        if (newUser == null)

            return ResponseEntity.ok().body(new User(null, "User NOT found any user on database", "", "", new ArrayList<>(), new ArrayList<>()));
        else {
            newUser.setPassword(null);
            return ResponseEntity.ok().body(newUser);
        }
    }

    @PostMapping("/addWriter")
    public ResponseEntity<Writer> addWriter(@RequestBody Writer writer) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/addWriter").toUriString());
        Writer newWriter = adminService.saveWriter(writer);
        return ResponseEntity.created(uri).body(newWriter);
    }

    @PostMapping("/deleteWriter")
    public ResponseEntity<Writer> deleteWriter(Long id) {
        Writer newWriter = adminService.deleteWriter(id);
        if (newWriter == null)
            return ResponseEntity.ok().body(new Writer(null, "Writer NOT found any user on database", "", "", new ArrayList<>()));
        else
            return ResponseEntity.ok().body(newWriter);
    }

    @PostMapping("/updateWriter")
    public ResponseEntity<Writer> updateWriter(@RequestBody Writer writer) {
        Writer updateWriter = adminService.updateWriter(writer);
        if (updateWriter == null)
            return ResponseEntity.ok().body(new Writer(null, "Writer NOT found any user on database", "", "", new ArrayList<>()));
        else
            return ResponseEntity.ok().body(adminService.updateWriter(updateWriter));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/addBook").toUriString());
        return ResponseEntity.created(uri).body(adminService.saveBook(book));

    }

    @PostMapping("/addBookToWriter")
    public ResponseEntity<Book> addBookToWriter(@RequestBody BookToWriter form) {
        adminService.addBookToWriter(form.getBookId(), form.getWriterId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<Book> removeBook(@RequestBody Book book) {

        Book deletedBook = adminService.deleteBook(book.getId());
        if (deletedBook == null)

            return ResponseEntity.ok().body(new Book(null, null, "Writer NOT found any user on database", "", null));
        else {
            return ResponseEntity.ok().body(deletedBook);
        }

    }

    @PostMapping("/updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path(path + "/deleteUser").toUriString());
        return ResponseEntity.created(uri).body(adminService.updateBook(book));
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
