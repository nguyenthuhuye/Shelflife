package com.example.shelflife.services.impl;

import com.example.shelflife.DTOs.BookListDTO;
import com.example.shelflife.DTOs.BooksDTO;
import com.example.shelflife.DTOs.BooksUpdateDTO;
import com.example.shelflife.authentications.UserPrincipal;
import com.example.shelflife.entities.Books;
import com.example.shelflife.entities.User;
import com.example.shelflife.repository.BooksRepositoty;
import com.example.shelflife.repository.UserRepository;
import com.example.shelflife.services.BooksService;
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
public class BooksServicesImpl implements BooksService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BooksRepositoty booksRepositoty;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public void register(BooksDTO dto, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Books books = new Books();
        books.setTitle(dto.getTitle());
        books.setAuthor(dto.getAuthor());
        books.setGenre(dto.getGenre());
        books.setPublicationYear(dto.getPublicationYear());
        books.setFeedback(dto.getFeedback());
        books.setCreatedDate(LocalDateTime.now());
        books.setCreatedUser(user.getId());
        booksRepositoty.save(books);
    }

    @Override
    public void update(String id, BooksUpdateDTO dto, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Books books = booksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books Invalid!"));
        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            books.setTitle(dto.getTitle());
        }
        if (dto.getAuthor() != null && !dto.getAuthor().isEmpty()) {
            books.setAuthor(dto.getAuthor());
        }
        if (dto.getGenre() != null && !dto.getGenre().isEmpty()) {
            books.setGenre(dto.getGenre());
        }
        if(dto.getFeedback() != null && !dto.getFeedback().isEmpty()) {
            books.setFeedback(dto.getFeedback());
        }
        books.setPublicationYear(dto.getPublicationYear());
        books.setUpdatedDate(LocalDateTime.now());
        books.setUpdatedUser(user.getId());
        booksRepositoty.save(books);
    }

    @Override
    public void registerFeedback(BooksDTO dto, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Books books = new Books();
        books.setTitle(dto.getTitle());
        books.setAuthor(dto.getAuthor());
        books.setGenre(dto.getGenre());
        books.setPublicationYear(dto.getPublicationYear());
        books.setFeedback(dto.getFeedback());
        books.setCreatedDate(LocalDateTime.now());
        books.setCreatedUser(user.getId());
        booksRepositoty.save(books);

    }

    @Override
    public void updateFeedback(String id, BooksUpdateDTO dto, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Books books = booksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books Invalid!"));
        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            books.setTitle(dto.getTitle());
        }
        if (dto.getAuthor() != null && !dto.getAuthor().isEmpty()) {
            books.setAuthor(dto.getAuthor());
        }
        if (dto.getGenre() != null && !dto.getGenre().isEmpty()) {
            books.setGenre(dto.getGenre());
        }
        if(dto.getFeedback() != null && !dto.getFeedback().isEmpty()) {
            books.setFeedback(dto.getFeedback());
        }
        books.setPublicationYear(dto.getPublicationYear());
        books.setUpdatedDate(LocalDateTime.now());
        books.setUpdatedUser(user.getId());
        booksRepositoty.save(books);
    }

    @Override
    public void remove(String id, UserPrincipal currentUser) throws IOException {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Books books = booksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books Invalid!"));
        booksRepositoty.delete(books);
    }

    @Override
    public BooksDTO info(String id, UserPrincipal currentUser) {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Books books = booksRepositoty.findById(new ObjectId(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Books Invalid!"));
        return mapper.map(books, BooksDTO.class);
    }

    @Override
    public BookListDTO list(Integer page, Integer pageSize, UserPrincipal currentUser) {
        User user = userRepository.findById(new ObjectId(currentUser.getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "title");
        List<BooksDTO> booksDTOS = new ArrayList<>();
        int totalPage = 0;
        long totalSize = 0;

        Page<Books> pageAdmin = booksRepositoty.findAll(pageable);
        totalPage = pageAdmin.getTotalPages();
        totalSize = pageAdmin.getTotalElements();
        booksDTOS = pageAdmin.stream().map(o -> mapper.map(o, BooksDTO.class)).collect(Collectors.toList());

        BookListDTO bookListDTO = new BookListDTO();
        bookListDTO.setBooks(booksDTOS);
        bookListDTO.setTotalPage(totalPage);
        bookListDTO.setTotalSize(totalSize);

        return bookListDTO;
    }


}
