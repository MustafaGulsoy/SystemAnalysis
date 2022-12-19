package com.UlimaStella.Doga_Server_Demo.services.book;

import com.UlimaStella.Doga_Server_Demo.domain.Book;
import com.UlimaStella.Doga_Server_Demo.domain.Role;
import com.UlimaStella.Doga_Server_Demo.domain.User;
import com.UlimaStella.Doga_Server_Demo.domain.Writer;
import com.UlimaStella.Doga_Server_Demo.repo.BookRepo;
import com.UlimaStella.Doga_Server_Demo.repo.RoleRepo;
import com.UlimaStella.Doga_Server_Demo.repo.UserRepo;
import com.UlimaStella.Doga_Server_Demo.repo.WriterRepo;
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
    private final BookRepo bookRepo;
    private final WriterRepo writerRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Book saveBook(Book book) {
        log.info("Saving new book to the database");
        return bookRepo.save(book);
    }

    @Override
    public Book deleteBook(long bookId) {
        Book book = bookRepo.findBookById(bookId);
        if (book != null) {
            log.info("Deleting book id : {} from the database", bookId);
            bookRepo.deleteById(bookId);
            return book;
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        Book bookFromDb = bookRepo.findBookById(book.getId());
        // crush the variables of the object found

        if (bookFromDb != null) {
            log.info("Update Writer (id : {}) from the database", book.getId());

            bookFromDb.setName(book.getName());
            bookFromDb.setWriter(book.getWriter());
            bookFromDb.setDetail(book.getDetail());
            bookFromDb.setPublishDate(book.getPublishDate());
            return bookFromDb;
        } else return null;
    }

    @Override
    public void purchaseBook(long userId, long bookId) {
        User user = userRepo.findUserById(userId);
        Book book = bookRepo.findBookById(bookId);
        user.getOrderedBooks().add(book);

    }

    @Override
    public User saveUser(User user) {


        if (userRepo.findByUsername(user.getUsername()) != null) {
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
            userFromDb.setName(user.getName());
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
    public void addBookToWriter(Long bookId, Long writerId) {

        Book book = bookRepo.findBookById(bookId);
        Writer writer = writerRepo.findWriterById(writerId);
        if (book == null)
            return;
        if (writer == null)
            return;
        if (book.getWriter() == null) {
            log.info("Adding {} to {} on the database", book.getName(), writer.getName());
            writer.getBooks().add(book);
            book.setWriter(writer);
        } else log.info("Book ({}) has already has a writer", book.getName());

    }

    @Override
    public List<User> getUsers() {

        log.info("getting all user from the database");
        return userRepo.findAll();
    }

    @Override
    public Writer deleteWriter(Long id) {
        Writer permWriter = writerRepo.findWriterById(id);
        if (permWriter != null) {
            log.info("Deleting user (id : {}) from the database", id);
            writerRepo.delete(permWriter);
            return permWriter;
        } else return null;
    }

    @Override
    public Writer updateWriter(Writer writer) {
        Writer writerFromDb = writerRepo.findWriterById(writer.getId());
        // crush the variables of the object found

        if (writerFromDb != null) {
            log.info("Update Writer (id : {}) from the database", writer.getId());
            writerFromDb.setName(writer.getName());
            writerFromDb.setSurname(writer.getSurname());
            writerFromDb.setDetail(writer.getDetail());
            writerFromDb.setBooks(writer.getBooks());
            writerRepo.save(writerFromDb);
            return writerFromDb;
        } else return null;
    }

    @Override
    public Writer saveWriter(Writer writer) {
        log.info("Saving new writer (name : {}) to the database", writer.getName());
        writerRepo.save(writer);
        return writer;
    }

}
