package com.example.shelflife.entities;

import com.example.shelflife.enums.RoleEnum;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class User extends BaseEntity{
    @Id
    private ObjectId id;

    private String username;

    private String password;

    private String email;

    private RoleEnum role;
}
