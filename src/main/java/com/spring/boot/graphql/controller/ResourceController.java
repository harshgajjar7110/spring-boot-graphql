package com.spring.boot.graphql.controller;

import com.spring.boot.graphql.service.GraphQlService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/books")
public class ResourceController {
    @Autowired
    GraphQlService graphQlService;

    @PostMapping
    public ResponseEntity<Object> getAllUser(@RequestBody String query) {
        ExecutionResult execute = graphQlService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
