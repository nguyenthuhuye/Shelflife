package com.example.shelflife.repository;

import com.example.shelflife.entities.Books;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositoty extends MongoRepository<Books, ObjectId> {
}
