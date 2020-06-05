package com.spring.boot.graphql.service;

import com.spring.boot.graphql.modal.User;
import com.spring.boot.graphql.repository.UserRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class UserDataFetcher {

    @Autowired
    UserRepository userRepository;


    public DataFetcher<User> getUser() {
        return environment -> {
            String id = environment.getArgument("id").toString();
            return userRepository.findById(id).orElse(null);
        };


    }

    public DataFetcher<List<User>> getAllUser() {
        return dataFetchingEnvironment -> {

            return userRepository.findAll();
        };
    }

    public DataFetcher<Object> addUser() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();

            User save = userRepository.save(new User(null, arguments.get("name").toString(), arguments.get("address").toString()));
            return save;
        };
    }

    public DataFetcher<Object> deleteUser() {
        return dataFetchingEnvironment -> {
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            userRepository.deleteById(arguments.get("id").toString());
            return true;
        };
    }

    public DataFetcher updateUser() {
        return dataFetchingEnvironment -> {
            User save = null;
            Map<String, Object> arguments = dataFetchingEnvironment.getArguments();
            User findById = userRepository.findById(arguments.get("id").toString()).orElse(null);
            if (Objects.nonNull(findById)) {

                findById.setAddress(arguments.get("address").toString());
                findById.setName(arguments.get("name").toString());
                save = userRepository.save(findById);
            }
            return save;

        };
    }


}
