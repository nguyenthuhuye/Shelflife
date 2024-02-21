package com.example.shelflife.repository;

import com.example.shelflife.entities.UserBooks;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBooksRepositoty extends MongoRepository<UserBooks, ObjectId> {

    UserBooks getUserBooksByTitle(String title);

    UserBooks findByUserId(String id);
}
