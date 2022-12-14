package com.UlimaStella.Doga_Server_Demo.repo;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepo  extends JpaRepository<Book,Long> {

    Book findByName(String name);


}
