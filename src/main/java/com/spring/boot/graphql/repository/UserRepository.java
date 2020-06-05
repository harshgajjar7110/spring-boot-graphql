package com.spring.boot.graphql.repository;


import com.spring.boot.graphql.modal.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
}
