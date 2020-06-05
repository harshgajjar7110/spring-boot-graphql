# spring-boot-graphql


#User Information
 /rest/user is the REST resource which can fetch User information
 DataFetchers are Interfaces for RuntimeWiring of GraphQL with JpaRepository
Sample GraphQL Scalar Queries
 Accessible under http://localhost:8091/rest/user
 Few Example of Query and Mutation
*  allUser { allUser { id name address } }
*  User { user(id: "123") { id name address }
*  deleteUser deleteUser(id:"5eda7925392d4d30ccc080a6")
*  updateUser updateUser(id:"5eda7925392d4d30ccc080a6", name:"harsh",address:"###"){ id name}
