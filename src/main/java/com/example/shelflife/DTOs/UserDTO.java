package com.example.shelflife.DTOs;

import com.example.shelflife.enums.RoleEnum;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class UserDTO {

    private ObjectId id;

    private String username;

    private String password;

    private String email;

    private RoleEnum role;
}
