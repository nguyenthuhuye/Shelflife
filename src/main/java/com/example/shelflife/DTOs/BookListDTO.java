package com.example.shelflife.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class BookListDTO {
    List<BooksDTO> books;

    int totalPage;

    long totalSize;
}
