package com.example.shelflife.services.impl;

import com.example.shelflife.DTOs.*;
import com.example.shelflife.authentications.UserPrincipal;
import com.example.shelflife.entities.Books;
import com.example.shelflife.entities.User;
import com.example.shelflife.entities.UserBooks;
import com.example.shelflife.enums.ReadingStatus;
import com.example.shelflife.repository.BooksRepositoty;
import com.example.shelflife.repository.UserBooksRepositoty;
import com.example.shelflife.repository.UserRepository;
import com.example.shelflife.services.UserBooksServices;
import org.bson.types.ObjectId;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserBooksServicesImpl implements UserBooksServices {

    @Autowired
    UserRepository userRepository;


    @Autowired
    BooksRepositoty booksRepositoty;

    @Autowired
    UserBooksRepositoty userBooksRepositoty;

    @Autowired
     DozerBeanMapper mapper;

    @Override
    public void register(UserBooksDTO dto, String id, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Books books = booksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books Invalid!"));

        UserBooks userBooks = new UserBooks();
        userBooks.setUserId(String.valueOf(user.getId()));
        userBooks.setBookId(String.valueOf(books.getId()));
        userBooks.setTitle(dto.getTitle());
        userBooks.setStatus(ReadingStatus.URREAD);
        userBooks.setCreatedDate(LocalDateTime.now());
        userBooks.setCreatedUser(user.getId());
        userBooksRepositoty.save(userBooks);
    }

    @Override
    public void update(String id, UserBooksUpdateDTO dto, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        UserBooks userBooks = userBooksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserBooks Invalid!"));

        Books books = booksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books Invalid!"));

        if (dto.getUserId() != null && !dto.getUserId().isEmpty()) {
            userBooks.setTitle(dto.getTitle());
        }
//        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) {
//            ReadingStatus readingStatus = ReadingStatus.valueOf(String.valueOf(dto.getStatus()));
//            if (readingStatus == null)
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Status!");
//
//            userBooks.setStatus(readingStatus);
//        }

        userBooks.setUserId(String.valueOf(user.getId()));
        userBooks.setBookId(String.valueOf(books.getId()));
        userBooks.setTitle(dto.getTitle());
        userBooks.setStatus(dto.getStatus());
        userBooks.setUpdatedDate(LocalDateTime.now());
        userBooks.setUpdatedUser(user.getId());
        userBooksRepositoty.save(userBooks);
    }

    @Override
    public void remove(String id, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        UserBooks userBooks = userBooksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserBooks Invalid!"));

        userBooksRepositoty.delete(userBooks);

    }

    @Override
    public UserBooksDTO info(String id, UserPrincipal currentUser) {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        UserBooks userBooks = userBooksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserBooks Invalid!"));
        return mapper.map(userBooks, UserBooksDTO.class);
    }

    @Override
    public UserBookListDTO list(Integer page, Integer pageSize, UserPrincipal currentUser) {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "title");
        List<UserBooksDTO> userBooksDTOS = new ArrayList<>();
        int totalPage = 0;
        long totalSize = 0;

        Page<UserBooks> pageAdmin = (Page<UserBooks>) userBooksRepositoty.findByUserId(currentUser.getId());
        totalPage = pageAdmin.getTotalPages();
        totalSize = pageAdmin.getTotalElements();
        userBooksDTOS = pageAdmin.stream().map(o -> mapper.map(o, UserBooksDTO.class)).collect(Collectors.toList());

        UserBookListDTO userBookListDTO = new UserBookListDTO();
        userBookListDTO.setUserBooks(userBooksDTOS);
        userBookListDTO.setTotalPage(totalPage);
        userBookListDTO.setTotalSize(totalSize);

        return userBookListDTO;
    }

}
