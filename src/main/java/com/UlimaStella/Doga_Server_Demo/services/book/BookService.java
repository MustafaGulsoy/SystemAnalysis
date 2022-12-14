package com.UlimaStella.Doga_Server_Demo.services.book;

import com.UlimaStella.Doga_Server_Demo.domain.Book;

public interface BookService {

    void saveBook(Book book);
    void deleteBook(Book book);
    void updateBook(Book book);
    void purchaseBook(long userId,long bookId);



}
