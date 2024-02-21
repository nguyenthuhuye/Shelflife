package com.example.shelflife.DTOs;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class BooksDTO {

    private ObjectId id;

    private String title;

    private String author;

    private int publicationYear;

    private String genre;

    private String feedback;
}
