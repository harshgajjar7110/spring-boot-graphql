package com.spring.boot.graphql.service;

import com.spring.boot.graphql.modal.Book;
import com.spring.boot.graphql.modal.User;
import com.spring.boot.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class BookDataFetcher {
    @Autowired
    BookRepository bookRepository;


    public DataFetcher<Book> getBook() {
        return environment -> {
            User source = environment.getSource();
            Book byUserId = bookRepository.findByUserId(source.getId()).orElse(new Book());
            return byUserId;
        };


    }

    public DataFetcher<List<Book>> getAllBook() {
        return dataFetchingEnvironment -> {

            return bookRepository.findAll();
        };
    }

    public DataFetcher<Object> addBook() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();

            Book save = bookRepository.save(new Book(null, arguments.get("name").toString(), arguments.get("userId").toString()));
            return save;
        };
    }

    public DataFetcher<Object> deleteBook() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            bookRepository.deleteById(arguments.get("id").toString());
            return true;
        };
    }

    public DataFetcher updateBook() {
        return dataFetchingEnvironment -> {
            Book save = null;
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            Book findById = bookRepository.findById(arguments.get("id").toString()).orElse(null);
            if (Objects.nonNull(findById)) {

                findById.setName(arguments.get("name").toString());
                findById.setUserId(arguments.get("id").toString());
                save = bookRepository.save(findById);
            }
            return save;

        };
    }


    public DataFetcher<Book> getUU() {
        return dataFetchingEnvironment -> {
            return new Book("1", "1", "1");
        };
    }
}
