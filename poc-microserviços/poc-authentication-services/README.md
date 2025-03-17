# POC Spring Microservices - Authentication Services

This repository is for study, testing and experimenting applications using [Spring boot](https://spring.io/), [Java](https://www.java.com/pt-BR/) and [MariaDB](https://mariadb.org/), more specifically testing how microservice can be develop, communicate via REST APIs and some other interections. If you found any problem, error, difficulty or have any suggestions, it will be a pleasure receive your code review, suggestions and/or tips. :raised_hands:

This README deals with installation details, application startup guide, dependencies and more. So you must have an enviroment setup with Java, Maven and MariaDB to run the application, if need help use this [tutorial for Java setup in Linux](https://www.baeldung.com/ubuntu-install-jdk) or [tutorial for Java setup in Linux](https://www.baeldung.com/openjdk-windows-installation), this [tutorial for maven setup in Windows and Linux](https://www.baeldung.com/install-maven-on-windows-linux-mac) and this [tutorial for mariaDB setup](https://www.tutorialspoint.com/mariadb/mariadb_installation.htm). The following versions installed on my computer to start the application is:

- Java - version 17
- Maven - version 3.8.3
- MariaDB - version 10.4 (But you can use Docker too 😄)

Attention: Don't forget to change lines 48 and 49 of the [application.yml](https://github.com/Eduk29/spring-playground/blob/main/poc-authentication-services/src/main/resources/application.yml) file with your database credentials.

This project use some patterns like [Semantic Commit Messages](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716) and [Git flow](https://nvie.com/posts/a-successful-git-branching-model/).

Live version will coming soon. To run the application in your pc, follow the Installation and Start Application section.

## Installation

This project was bootstrapped with [Spring Initializr](https://start.spring.io/)

**1 -** Clone the project and install the dependencies:

```bash
git clone https://github.com/Eduk29/spring-playground/tree/main/poc-authentication-services
cd poc-authentication-services
mvn clean install
```

**2 -** Keep hacking! :metal:

## Start Application

If you want to run the application in your pc, follow these instructions.

**1 -** Open a terminal in project root directory and run the following instruction:

```bash
mvn spring-boot:run
```

**This command terminal must be open during application execution.**

## Test the Application

**1 -** Using some API test environment like Postman, Insomnia, Curl or another familiar api caller environment to you and create a new user to application:

```bash
URL: http://localhost:8080/api/v1/users/register
Body:
{
    "password": "12345678",
    "username": "admin",
    "personId": 1,
    "roles": ["ADMIN"]
}
```

If you register the user correctly, the return status should be 200 and the response body contains the user information created.

**2 -** Now you can login with this user:

```bash
URL: http://localhost:8080/api/v1/authentication/login
Body:
{
    "password": "12345678",
    "username": "admin"
}
```

**This command terminal must be open during application execution.**

## API Documentation

If you want to see de API documentantion in Swagger, please start the application and open [OpenAPI definition](http://localhost:8080/api/v1/swagger-ui/index.html#/).

## API Resources

- User login :white_check_mark:
- JWT Validation :white_check_mark:
- JWT Renew :white_check_mark:
- Logged user information :white_check_mark:
- User registration :white_check_mark:
- User list paginated :white_check_mark:
- User details :white_check_mark:
- User update :white_check_mark:
- User delete by Admin profile :white_check_mark:
- User promote to Admin by Admin profile :white_check_mark:
- Role list paginated
- Role details
- Role creation by Admin profile
- Role attribuition by Admin profile

## Dependecies

- JSON Web Token (JWT)
- Lombok
- MariaDB
- Spring Boot Data JPA
- Spring Boot DevTools
- Spring Boot Security
- Spring Boot Web
- Swagger 3

## Developer :computer:

José Eduardo Trindade E Marques  
<edu.temarques@gmail.com>
