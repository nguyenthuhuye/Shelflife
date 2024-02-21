package com.example.shelflife.controllers;

import com.example.shelflife.DTOs.BookListDTO;
import com.example.shelflife.DTOs.BooksDTO;
import com.example.shelflife.DTOs.BooksUpdateDTO;
import com.example.shelflife.authentications.UserPrincipal;
import com.example.shelflife.services.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;

@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Book API")
@CrossOrigin
@SecurityRequirement(name = "bearer-key")
public class BookController {

    @Autowired
    BooksService booksService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @Operation(summary = "List")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public BookListDTO list(@RequestParam(name = "page") Integer page, @RequestParam(name = "pageSize") Integer pageSize,
                            @AuthenticationPrincipal UserPrincipal currentUser) {
        return booksService.list( page, pageSize, currentUser);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    @Operation(summary = "Info")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public BooksDTO info( @PathVariable(name = "id") String id,
                         @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) {
        return booksService.info(id, currentUser);
    }

    @RequestMapping(value = "/book/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void register(@ModelAttribute @Valid BooksDTO dto,
                         @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        booksService.register(dto, currentUser);
    }

    @RequestMapping(value = "/book/update/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void update(@PathVariable(name = "id") String id,
                       @ModelAttribute @Valid BooksUpdateDTO dto,
                       @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        booksService.update( id, dto, currentUser);
    }

    @RequestMapping(value = "/book/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void registerFeedback(@ModelAttribute @Valid BooksDTO dto,
                         @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        booksService.register(dto, currentUser);
    }

    @RequestMapping(value = "/book/update/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateFeedback(@PathVariable(name = "id") String id,
                       @ModelAttribute @Valid BooksUpdateDTO dto,
                       @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        booksService.update( id, dto, currentUser);
    }

    @RequestMapping(value = "/book/remove/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(@PathVariable(name = "id") String id,
                       @Parameter(hidden = true) @AuthenticationPrincipal UserPrincipal currentUser) throws IOException {
        booksService.remove(id, currentUser);
    }
}
