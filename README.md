# User Management API (Spring Boot)

## 📌 Overview

This project is a REST API built using Spring Boot for managing users and their orders.

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
* MySQL

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
