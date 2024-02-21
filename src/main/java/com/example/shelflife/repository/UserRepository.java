package com.example.shelflife.repository;

import com.example.shelflife.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findFirstByEmail(String email);

    User getUserByEmail(String email);
}
