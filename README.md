# spring-openapi-prototype
Using the the code in https://github.com/michael-it-valencia/spring-openapi-jpa.git as base for openapi , we will implement a full API application using Spring , Spring-JPA and MariaDB.We will be developing a user management API using openAPI to build the API definitions

Activities
1 DB Setup using docker - run mariadb using docker compose
    - docker compose up -d mariadb
2 Update dependencies in build.gradle 
3 Update openapi.yaml to define the required api definitions
4 Copy the generated API definitions in the UsersApi in UsersApiController for implementation
5 Define Models for the DB
6 Create the table in the DB using Spring JPA and validate if table is created
    - Application properties values
    - ## MARIADB
        spring.datasource.url=jdbc:mariadb://localhost:3306/demodb
        spring.datasource.username=user
        spring.datasource.password=password

        spring.jpa.generate-ddl=true
        #spring.jpa.hibernate.ddl-auto=update
        spring.jpa.hibernate.ddl-auto=create
7 Define Spring Repository class
8 Define the Service Classes
9 Update Controller to implement the API
10 Test the API with Spring Swagger
    - Run the application 
       ./gradlew bootRun
       http://localhost:8080/swagger-ui/index.html         