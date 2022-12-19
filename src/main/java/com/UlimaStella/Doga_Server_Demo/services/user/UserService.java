package com.UlimaStella.Doga_Server_Demo.services.user;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.domain.Writer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User getUser(String username);
    List<Book> getBooks(Integer pageNo, Integer pageSize);

    List<Writer> getWriters(Integer pageNo, Integer pageSize);
    void purchaseBook(Long bookId,Long userId);
}
