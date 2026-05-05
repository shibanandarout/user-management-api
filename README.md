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

### Create User

POST /userdto

### Get All Users

GET /userdto

### Get User by ID

GET /userdto/{id}

### Update User

PUT /userdto/{id}

### Patch User

PATCH /userdto/{id}

### Delete User

DELETE /userdto/{id}

## ⚙️ Validation

* Name should not be empty
* Product should not be empty
* Price must be greater than 0

## ❗ Exception Handling

* UserNotFoundException → 404
* Validation errors → 400
* Generic errors → 500

## 👨‍💻 Author

Shibananda Rout
