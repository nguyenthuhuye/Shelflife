package com.example.shelflife.DTOs;

import com.example.shelflife.enums.ReadingStatus;
import lombok.Data;

@Data
public class UserBooksUpdateDTO {

    private String userId;

    private String bookId;

    private String title;

    private ReadingStatus status;

}
