# User Management API (Spring Boot)

## 📌 Overview

A backend RESTful API built using Spring Boot to manage users and their associated orders. 
The application follows a layered architecture and implements robust validation and global exception handling to ensure reliable and scalable API performance.

## 🚀 Features

* CRUD operations (Create, Read, Update, Delete)
* DTO-based architecture
* Validation using annotations
* Global Exception Handling
* Nested object validation

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL

## 📡 API Endpoints

## 📌 API Endpoints

| Method | Endpoint        | Description          |
|--------|---------------|----------------------|
| GET    | /users        | Get all users        |
| GET    | /users/{id}   | Get user by ID       |
| POST   | /users        | Create new user      |
| PATCH  | /users/{id}   | Update user partially|
| DELETE | /users/{id}   | Delete user          |

* Name should not be empty
* Product should not be empty
* Price must be greater than 0

## ❗ Exception Handling

* UserNotFoundException → 404
* Validation errors → 400
* Generic errors → 500

## 👨‍💻 Author

Shibananda Rout
