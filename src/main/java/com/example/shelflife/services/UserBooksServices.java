package com.example.shelflife.services;

import com.example.shelflife.DTOs.*;
import com.example.shelflife.authentications.UserPrincipal;
import com.example.shelflife.entities.UserBooks;

import java.io.IOException;

public interface UserBooksServices {

    void register(UserBooksDTO dto, String id, UserPrincipal currentUser) throws IOException;

    void update(String id, UserBooksUpdateDTO dto, UserPrincipal currentUser) throws IOException;

    void remove(String id, UserPrincipal currentUser) throws IOException;

    UserBooksDTO info(String id, UserPrincipal currentUser);

    UserBookListDTO list(Integer page, Integer pageSize, UserPrincipal currentUser);
}
