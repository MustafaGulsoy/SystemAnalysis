package com.UlimaStella.Doga_Server_Demo.services.user;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BookRepo bookRepo;
    private final WriterRepo writerRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.error("User found in the database : {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }


    @Override
    public User getUser(String username) {

        log.info("getting {} from the database", username);

        return userRepo.findByUsername(username);
    }

    @Override
    public List<Book> getBooks(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Book> pagedResult = bookRepo.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Book>();
        }

    }

    @Override
    public List<Writer> getWriters(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Writer> pagedResult = writerRepo.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Writer>();
        }
    }

    @Override
    public void purchaseBook(Long bookId,Long userId) {

        User user = userRepo.findUserById(userId);
        Book book = bookRepo.findBookById(bookId);
        user.getOrderedBooks().add(book);
    }
}
