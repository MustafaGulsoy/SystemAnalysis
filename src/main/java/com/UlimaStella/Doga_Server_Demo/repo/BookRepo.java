package com.UlimaStella.Doga_Server_Demo.repo;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.google.api.client.http.HttpMethods.DELETE;


public interface BookRepo extends JpaRepository<Book, Long> {

    Book findByName(String name);

    Book findBookById(Long id);

    @Override
    void deleteById(Long aLong);

    Book removeById(Long id);


}
