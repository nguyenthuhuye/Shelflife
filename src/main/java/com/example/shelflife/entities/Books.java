package com.example.shelflife.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Book")
public class Books extends BaseEntity{

    @Id
    private ObjectId id;

    private String title;

    private String author;

    private int publicationYear;

    private String genre;

    private String feedback;
}
