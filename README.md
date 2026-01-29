CampusHelp

University IT Ticketing & Issue Management System

Project Overview

CampusHelp is a centralized IT ticketing platform designed for university environments.
Students, professors, and administrative staff can submit IT-related issues, while IT personnel manage, assign, and resolve tickets efficiently.

The system demonstrates real-world backend architecture using Spring Boot, with structured layers, meaningful business logic, and RESTful APIs.

Team Members

Erblin Emini

Lorik Alimi

Ardit Ismaili

Technologies Used

Java 21

Spring Boot

Spring Data JPA (Hibernate)

Gradle (Groovy DSL)

PostgreSQL

JUnit 5

Mockito

Git & GitHub

RESTful APIs

Jakarta Validation

System Architecture

The project follows a layered architecture:

Controller Layer  → REST API endpoints
Service Layer     → Business logic
Repository Layer  → Database access (JPA)
Entity Layer      → Database models
DTO Layer         → Request/Response mapping


This separation ensures clean code, maintainability, testability, and scalability.

Database Design

Main entities include:

User

Ticket

TicketComment

TicketAttachment

TicketStatusHistory

Key Relationships

One User to many Tickets

One Ticket to many Comments

One Ticket to many Attachments

One Ticket to many Status History entries

Core Features

User management

Ticket creation and tracking

Ticket assignment to IT staff

Ticket status workflow

Comments and internal notes

Priority and category handling

Filtering, searching, and sorting

DTO mapping

Validation and error handling

RESTful API design

Unit testing at service and controller layers

API Endpoints (Examples)
GET    /api/users
POST   /api/users
GET    /api/tickets
POST   /api/tickets
PUT    /api/tickets/{id}
DELETE /api/tickets/{id}
GET    /api/tickets/{id}/comments


All endpoints return JSON responses and use proper HTTP status codes.

Testing

The project includes:

Service layer tests

Controller layer tests

Testing technologies used:

JUnit 5

Mockito

Spring Boot Test

How to Run the Project
1. Clone the repository
git clone https://github.com/<your-organization>/CampusHelp.git
cd CampusHelp

2. Configure the database

Edit src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/campushelp
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Ensure PostgreSQL is running and the database exists.

3. Run the application
./gradlew bootRun


Or run CampusHelpApplication.java from your IDE.

4. Access the API
http://localhost:8080


You can test the endpoints using Postman or Insomnia.

Git Workflow

The project uses a branch-based workflow:

person-x → develop → main


Each team member works on their own branch

The develop branch is used for integration

The main branch contains stable releases

Pull Requests are used for merging

Documentation

This repository contains:

Source code

Unit tests

README documentation

Meaningful commit history

Expected Outcome

CampusHelp is a production-like backend system that demonstrates:

Solid understanding of Spring Boot

Clean architecture principles

Realistic business logic

Proper Git collaboration

REST API best practices
