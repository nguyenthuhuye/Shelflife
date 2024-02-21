package com.example.shelflife.services.impl;

import com.example.shelflife.DTOs.SignupRequest;
import com.example.shelflife.DTOs.UserDTO;
import com.example.shelflife.entities.User;
import com.example.shelflife.enums.RoleEnum;
import com.example.shelflife.repository.UserRepository;
import com.example.shelflife.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setRole(RoleEnum.USER);
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(createdUser.getUsername());
        userDTO.setEmail(createdUser.getEmail());
        return userDTO;
    }
}
