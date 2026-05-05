# User Management API (Spring Boot)

## 📌 Overview

A backend RESTful API built using Spring Boot to manage users and their associated orders.  
The application follows a layered architecture and implements robust validation and global exception handling to ensure reliable and scalable API performance.

---

## 🚀 Features

- CRUD operations for User and Order management  
- DTO-based architecture to separate API and database layers  
- Input validation to ensure data integrity  
- Global exception handling for consistent error responses  
- Nested object validation for handling complex request structures  
- RESTful API design following best practices  

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- PostgreSQL  

---

## 🔥 Key Concepts Used

- DTO Pattern  
- One-to-Many Mapping  
- Validation (`@NotNull`, `@NotBlank`)  
- Global Exception Handling (`@RestControllerAdvice`)  
- Use of ResponseEntity for handling HTTP responses and status codes  
- PATCH API implementation  

---

## 📡 API Endpoints

| Method | Endpoint        | Description           |
|--------|---------------|-----------------------|
| GET    | /users        | Get all users         |
| GET    | /users/{id}   | Get user by ID        |
| POST   | /users        | Create new user       |
| PATCH  | /users/{id}   | Update user partially |
| DELETE | /users/{id}   | Delete user           |

---

## ✅ Validation Rules

- Name should not be empty  
- Product should not be empty  
- Price must be greater than 0  

---

## ❗ Exception Handling

- UserNotFoundException → 404 Not Found  
- Validation errors → 400 Bad Request  
- Generic errors → 500 Internal Server Error  

---

## ▶️ How to Run

1. Clone the repository  
2. Open in Eclipse / IntelliJ  
3. Configure PostgreSQL in `application.properties`  
4. Run Spring Boot application  
5. Test APIs using Postman  

---

## 👨‍💻 Author

Shibananda Rout
