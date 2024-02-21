package com.example.shelflife.services;

import com.example.shelflife.DTOs.SignupRequest;
import com.example.shelflife.DTOs.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);
}
