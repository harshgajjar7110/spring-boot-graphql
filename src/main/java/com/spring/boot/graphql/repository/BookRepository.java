package com.spring.boot.graphql.repository;

import com.spring.boot.graphql.modal.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByUserId(String id);
}
