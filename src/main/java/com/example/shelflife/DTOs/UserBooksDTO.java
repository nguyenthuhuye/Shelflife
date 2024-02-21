package com.example.shelflife.DTOs;

import com.example.shelflife.enums.ReadingStatus;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UserBooksDTO {

    private ObjectId id;

    private String userId;

    private String bookId;

    private String title;

    private ReadingStatus status;

}
