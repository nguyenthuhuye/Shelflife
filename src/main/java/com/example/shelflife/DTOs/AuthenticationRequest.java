package com.example.shelflife.DTOs;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
