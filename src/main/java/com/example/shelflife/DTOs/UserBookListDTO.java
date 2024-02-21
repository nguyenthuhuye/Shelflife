package com.example.shelflife.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class UserBookListDTO {
    List<UserBooksDTO> userBooks;

    int totalPage;

    long totalSize;
}
