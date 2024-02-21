package com.example.shelflife.controllers;

import com.example.shelflife.DTOs.*;
import com.example.shelflife.authentications.UserPrincipal;
import com.example.shelflife.services.UserBooksServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "UserBook API")
@CrossOrigin
@SecurityRequirement(name = "bearer-key")
public class UserBookController {

    @Autowired
    UserBooksServices userBooksServices;

    @RequestMapping(value = "/userbook", method = RequestMethod.GET)
    @Operation(summary = "List")
    @PreAuthorize("hasAuthority('USER')")
    public UserBookListDTO list(@RequestParam(name = "page") Integer page, @RequestParam(name = "pageSize") Integer pageSize,
                                @AuthenticationPrincipal UserPrincipal currentUser) {
        return userBooksServices.list( page, pageSize, currentUser);
    }

    @RequestMapping(value = "/userbook/{id}", method = RequestMethod.GET)
    @Operation(summary = "Info")
    @PreAuthorize("hasAuthority('USER')")
    public UserBooksDTO info(@PathVariable(name = "id") String id,
                             @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) {
        return userBooksServices.info(id, currentUser);
    }

    @RequestMapping(value = "/userbook/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void register(@PathVariable(name = "id") String id,
                         @ModelAttribute @Valid UserBooksDTO dto,
                         @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        userBooksServices.register(dto, id, currentUser);
    }

    @RequestMapping(value = "/userbook/update/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update")
    @PreAuthorize("hasAuthority('USER')")
    public void update(@PathVariable(name = "id") String id,
                       @ModelAttribute @Valid UserBooksUpdateDTO dto,
                       @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        userBooksServices.update( id, dto, currentUser);
    }

    @RequestMapping(value = "/userbook/remove/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Remove")
    @PreAuthorize("hasAuthority('USER')")
    public void remove(@PathVariable(name = "id") String id,
                       @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        userBooksServices.remove(id, currentUser);
    }
}
