package com.UlimaStella.Doga_Server_Demo.services.book;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.repo.BookRepo;
import com.UlimaStella.Doga_Server_Demo.repo.RoleRepo;
import com.UlimaStella.Doga_Server_Demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BookRepo bookRepo;
    private final PasswordEncoder passwordEncoder;

       @Override
    public void saveBook(Book book) {



    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void purchaseBook(long userId, long bookId) {

    }
}
