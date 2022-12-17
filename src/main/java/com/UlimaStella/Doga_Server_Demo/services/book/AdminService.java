package com.UlimaStella.Doga_Server_Demo.services.book;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.domain.Role;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.domain.Writer;

import java.util.List;

public interface AdminService {

    Book saveBook(Book book);
    Book deleteBook(long book);
    Book updateBook(Book book);
    void purchaseBook(long userId,long bookId);
    User saveUser(User user);
    User deleteUser(long id);

    User updateUser(User user);

    Role saveRole(Role role);


    void addRoleToUser(String username, String roleName);
    void addBookToWriter(Long bookId, Long writerId);

    List<User> getUsers();


    Writer deleteWriter(Long writer);

    Writer updateWriter(Writer writer);

    Writer saveWriter(Writer writer);
}
