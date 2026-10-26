# Employee Service Microservice

## Project Overview

The Employee Service is a RESTful microservice developed using Java and Spring Boot to manage employee information. It provides CRUD APIs, stores employee records in MySQL, and communicates with the Department Service to validate department references.

## Technologies Used

- Java 21
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Jakarta Bean Validation
- Springdoc OpenAPI / Swagger UI
- Spring RestClient
- Postman
- Git and GitHub

## Project Architecture

The application follows a layered architecture.

- Controller: Handles REST API requests.
- Service: Implements employee business logic.
- Repository: Performs database operations.
- Entity: Represents employee records.
- Client: Communicates with the Department Service.
- Exception Handling: Handles application errors.

## Database Integration

Database Name: `employeedb`

Application Port: `8082`

Employee records are stored in MySQL using Spring Data JPA and Hibernate. Database persistence was verified by checking the records in MySQL.

## Employee Entity

The Employee entity contains:

- `id` — Unique employee identifier.
- `name` — Employee name.
- `email` — Employee email address.
- `designation` — Employee designation.
- `salary` — Employee salary.
- `departmentId` — Identifier of the associated department.

## REST API Endpoints

Base URL: `http://localhost:8082`

- POST `/employees` — Create an employee.
- GET `/employees` — Retrieve all employees.
- GET `/employees/{id}` — Retrieve an employee by ID.
- PUT `/employees/{id}` — Update employee details.
- DELETE `/employees/{id}` — Delete an employee.
- GET `/employees/department/{departmentId}` — Retrieve employees by department ID.

## Validation

Implemented request validation to check employee input data and reject invalid requests.

## Exception Handling

Implemented exception handling for invalid requests and employees that do not exist, returning appropriate error responses.

## Employee–Department Integration

Integrated Employee Service with Department Service using Spring RestClient.

Department Service URL: `http://localhost:8081/departments`

Employee Service validates department references through the Department Service. Both services run independently on ports `8081` and `8082`.

## Swagger / OpenAPI

Swagger UI provides documentation for the Employee Service APIs.

URL: `http://localhost:8082/swagger-ui/index.html`

## Postman Testing

Tested the Employee Service APIs using Postman, including:

- Creating employees.
- Retrieving all employees.
- Retrieving employees by ID.
- Updating employee details.
- Deleting employees.
- Retrieving employees by department ID.
- Testing validation and exception handling.
- Verifying employee records in MySQL.
- Testing Employee–Department integration.

## Project Information

Project: Employee and Department Microservices

Module: Employee Service

Purpose: To manage employee information through REST APIs, MySQL database integration, and communication with the Department Service.
