package com.example.shelflife.entities;

import com.example.shelflife.enums.ReadingStatus;
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
@Document(collection = "UserBook")
public class UserBooks extends BaseEntity{

    @Id
    private ObjectId id;

    private String userId;

    private String bookId;

    private String title;

    private ReadingStatus status;

}
