package com.UlimaStella.Doga_Server_Demo.services.user;

import com.UlimaStella.Doga_Server_Demo.domain.User;
import java.util.List;

public interface UserService {

    User getUser(String username);
    void purchaseBook(long bookId, long userId);
}
