package com.example.shelflife.controllers;

import com.example.shelflife.DTOs.SignupRequest;
import com.example.shelflife.DTOs.UserDTO;
import com.example.shelflife.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Signup API")
@CrossOrigin
@SecurityRequirement(name = "bearer-key")
public class SignupUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest ){
        UserDTO createdUser = authService.createUser(signupRequest);
        if (createdUser == null)
            return  new ResponseEntity<>("User is not created, try again later.", HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
