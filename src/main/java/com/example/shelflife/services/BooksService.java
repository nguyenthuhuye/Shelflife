package com.example.shelflife.services;

import com.example.shelflife.DTOs.BookListDTO;
import com.example.shelflife.DTOs.BooksDTO;
import com.example.shelflife.DTOs.BooksUpdateDTO;
import com.example.shelflife.authentications.UserPrincipal;

import java.io.IOException;

public interface BooksService {

    void register(BooksDTO dto, UserPrincipal currentUser) throws IOException;

    void update(String id, BooksUpdateDTO dto, UserPrincipal currentUser) throws IOException;

    void registerFeedback(BooksDTO dto, UserPrincipal currentUser) throws IOException;

    void updateFeedback(String id, BooksUpdateDTO dto, UserPrincipal currentUser) throws IOException;

    void remove(String id, UserPrincipal currentUser) throws IOException;

    BooksDTO info(String id, UserPrincipal currentUser);

    BookListDTO list(Integer page, Integer pageSize, UserPrincipal currentUser);
}
