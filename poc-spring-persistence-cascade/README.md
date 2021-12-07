# POC Spring Persistence with Cascade Tests

This repository is for study, testing and experimenting applications using [Spring boot](https://spring.io/), [Java](https://www.java.com/pt-BR/) and [MariaDB](https://mariadb.org/), more specifically testing cascade types (CascadeType) and interaction with nested entities.If you found any problem, error, difficulty or have any suggestions, it will be a pleasure receive your code review, suggestions and/or tips. :raised_hands:

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
$ git clone https://github.com/Eduk29/spring-playground/tree/main/poc-spring-persistence-cascade
$ cd poc-spring-persistence-cascade
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

2 - Open [http://localhost:8080/api/persons](http://localhost:8080/api/persons) and if all went well, when you open this address in the browser you will see a list of fictitious people registered in the system

## Dependecies

- Jackson Databind
- Javax Persistance API
- MariaDB
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Test
- Spring Boot DevTools
- Swagger 2
- Swagger UI

## Developer :computer:

José Eduardo Trindade E Marques  
edu.temarques@gmail.com
