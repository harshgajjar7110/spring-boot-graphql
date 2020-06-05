package com.spring.boot.graphql.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQlService {

    @Value("classpath:user.graphql")
    Resource resource;


    private GraphQL graphQL;

    @Autowired
    private UserDataFetcher userDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {


        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }


    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allUser", userDataFetcher.getAllUser())

                        .dataFetcher("user", userDataFetcher.getUser()))
                .type("User", typeWiring -> typeWiring.dataFetcher("book", bookDataFetcher.getBook()))
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("addUser", userDataFetcher.addUser())
                        .dataFetcher("deleteUser", userDataFetcher.deleteUser())
                        .dataFetcher("updateUser", userDataFetcher.updateUser())
                        .dataFetcher("addBook", bookDataFetcher.addBook())
                        .dataFetcher("deleteBook", bookDataFetcher.deleteBook())
                        .dataFetcher("updateBook", bookDataFetcher.updateBook())
                )
                .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
