# POC Spring Security with differents types of Authentication

This repository is for study, testing and experimenting applications using [Spring boot](https://spring.io/), [Java](https://www.java.com/pt-BR/) and [MariaDB](https://mariadb.org/), more specifically testing spring security types (Basic, using database and JWT).If you found any problem, error, difficulty or have any suggestions, it will be a pleasure receive your code review, suggestions and/or tips. :raised_hands:

This README deals with installation details, application startup guide, dependencies and more. So you must have an enviroment setup with Java, Maven and MariaDB to run the application, if need help use this [tutorial for Java setup](https://www.baeldung.com/ubuntu-install-jdk), this [tutorial for maven setup](https://www.baeldung.com/install-maven-on-windows-linux-mac) and this [tutorial for mariaDB setup](https://www.tutorialspoint.com/mariadb/mariadb_installation.htm). The following versions installed on my computer to start the application is:

- Java - version 11
- Maven - version 3.8.3
- MariaDB - version 10.6

Attention: Don't forget to change lines 48 and 49 of the [application.yml](https://github.com/Eduk29/spring-playground/blob/main/poc-spring-persistence-cascade/src/main/resources/application.yml) file with your database credentials.

This project use some patterns like [Semantic Commit Messages](https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716) and [Git flow](https://nvie.com/posts/a-successful-git-branching-model/).

Live version will coming soon. To run the application in your pc, follow the Installation and Start Application section.

## Installation

This project was bootstrapped with [Spring Initializr](https://start.spring.io/)

**1 -** Clone the project and install the dependencies:

```bash
$ git clone https://github.com/Eduk29/spring-playground/tree/main/poc-spring-security
$ cd poc-spring-security
$ mvn clean install
```

**2 -** Keep hacking! :metal:

## Start Application

If you want to run the application in your pc, follow these instructions.

1 - Open a terminal in project root directory and run the following instruction:

```bash
mvn spring-boot:run
```

**This command terminal must be open during application execution.**

2 - Open Postman and access [http://localhost:8080/api/authentication/sign-in](http://localhost:8080/api/authentication/sign-in) to authenticate user. 
In body tab, paste the following JSON code:

```bash
{
    "username": "tony.stark"
    "password": "12345678"
}
```

3 - If all went well, you receive a response with some user data. Copy the token code and when you call the API, use this code as Bearer Authorization Code to access successfully the API Data.

## API Endpoints

1 - Hello World 

    Endpoint: http://localhost:8080/api/hello-world
    Requirements:
        Authenticated: YES
        Role: USER

2 - User List

    Endpoint: http://localhost:8080/api/users
    Requirements:
        Authenticated: YES
        Role: ADMIN

3 - Sign In

    Endpoint: http://localhost:8080/api/authentication/sign-in
    Requirements:
        Authenticated: NO
        Role: -

## Application Uers

### In Memory Users

* User

    Username: user  
    Password: 1234  
    Roles: USER  

* Admin

    Username: admin
    Password: admin
    Roles: ADMIN

### Database Users

1 - Tony Stark
    Username: tony.stark
    Passwork: 12345678
    Roles: ADMIN, USER

2 - Steve Strange
    Username: steve.strange
    Passwork: 12345678
    Roles: USER

3 - Bruce Banner
    Username: bruce.banner
    Passwork: 12345678
    Roles: ADMIN, USER

4 - Steve Rogers
    Username: steve.rogers
    Passwork: 12345678
    Roles: USER

## API Tags

### spring-security-0.0.1

Basic security authentication with in memory authentication. Two users is provided to authentication:

* User
* Admin

Endpoints

* Hello World => Only USER role access
* User List => Only ADMIN role access

### spring-security-0.0.2

Basic security authentication with database authentication. Four users is provided to authentication:

* Tony Stark
* Steve Strange
* Bruce Banner
* Steve Rogers

Endpoints

* Hello World => Only USER role access
* User List => Only ADMIN role access

### spring-security-0.0.3

JWT authentication with database. Four users is provided to authentication:

* Tony Stark
* Steve Strange
* Bruce Banner
* Steve Rogers

Endpoints

* Hello World => Only USER role access
* User List => Only ADMIN role access
* Sign In => Not required permission to access

## Dependecies

- JWT Web Token
- MariaDB
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Test
- Spring Security
- Spring Security Test
- Spring Boot DevTools

## Developer :computer:

José Eduardo Trindade E Marques  
edu.temarques@gmail.com
