# Test course

Tools

DBMS            - MySQL
ORM             - Mybatis
Language Server - Java 11 (Spring Boot)
Dependency      - Maven

DBMS

- Create database with name: course
- The file schema is locate path: course/src/main/resources/etc/sql/v1.0__initial-setup.sql

Setup application
- $ mvn clean install
- $ mvn spring-boot:run

Swagger UI

- Open a browser with follow URL: http://localhost:9091/swagger-ui.html