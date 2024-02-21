package com.example.shelflife.DTOs;

import lombok.Data;

@Data
public class BooksUpdateDTO {

    private String title;

    private String author;

    private int publicationYear;

    private String genre;

    private String feedback;
}
