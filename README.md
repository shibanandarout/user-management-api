# User Management API (Spring Boot)

## 📌 Overview

User Management API is a secure RESTful backend application built using Spring Boot that manages users and their associated orders. The project follows a layered architecture with DTOs, validation, global exception handling, Spring Security, and JWT Authentication to provide secure and scalable REST APIs.

---

## 🚀 Features

- ✅ User CRUD Operations
- ✅ Order Management
- ✅ One-to-Many Relationship (User ↔ Orders)
- ✅ DTO-based Architecture
- ✅ Request Validation using Jakarta Validation
- ✅ Global Exception Handling
- ✅ Password Encryption using BCrypt
- ✅ JWT Authentication
- ✅ Secure Login API
- ✅ Protected REST APIs
- ✅ Stateless Authentication using Spring Security
- ✅ PostgreSQL Database Integration

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JJWT)
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- REST APIs
- Jakarta Validation
- Postman

---

## 🔥 Key Concepts Used

- DTO Pattern
- One-to-Many Mapping
- Spring Security
- JWT Authentication
- BCrypt Password Encoding
- Stateless Session Management
- Authentication Manager
- Custom UserDetailsService
- Validation (`@NotBlank`, `@NotEmpty`, `@Min`)
- Global Exception Handling (`@RestControllerAdvice`)
- ResponseEntity
- PATCH API Implementation

---

## 📂 Project Structure

```
src/main/java
│
├── config
│
├── controller
│
├── dto
│
├── entity
│
├── exception
│
├── jwt
│
├── repository
│
├── security
│
├── service
│
└── serviceImpl
```

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/auth/login` | User Login | Public |

---

### User APIs

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/user-dto` | Register User | Public |
| GET | `/user-dto` | Get All Users | JWT Required |
| GET | `/user-dto/{id}` | Get User By ID | JWT Required |
| PUT | `/user-dto/{id}` | Update User | JWT Required |
| PATCH | `/user-dto/{id}` | Partial Update | JWT Required |
| DELETE | `/user-dto/{id}` | Delete User | JWT Required |
| DELETE | `/user-dto` | Delete All Users | JWT Required |

---

## 🔐 JWT Authentication Flow

1. Register a new user
2. Login using email and password
3. Receive a JWT token
4. Add the token to the Authorization header
5. Access protected APIs

---

## 🔑 Authorization Header

```
Authorization: Bearer <YOUR_JWT_TOKEN>
```

---

## 📥 Sample Login Request

```json
{
    "email": "shibananda@gmail.com",
    "password": "123456"
}
```

---

## 📤 Sample Login Response

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## ✅ Validation Rules

### User

- Name should not be empty
- Name must be between 2 and 50 characters
- Email should not be empty
- Password should not be empty
- Password must contain at least 6 characters

### Order

- Product should not be empty
- Price must be greater than 100

---

## ❗ Exception Handling

- UserNotFoundException → 404 Not Found
- Validation Errors → 400 Bad Request
- Authentication Failure → 403 Forbidden
- Generic Exceptions → 500 Internal Server Error

---

## ▶️ How to Run

1. Clone the repository

```bash
git clone https://github.com/shibanandarout/user-management-api.git
```

2. Open the project in Eclipse or IntelliJ IDEA.

3. Configure PostgreSQL in `application.properties`.

4. Build the project.

```bash
mvn clean install
```

5. Run the Spring Boot application.

6. Test APIs using Postman.

---

## 🧪 Testing

The APIs were tested using Postman for:

- User Registration
- User Login
- JWT Token Generation
- Protected API Access
- CRUD Operations
- Validation
- Exception Handling

---

## 🔒 Security Features

- BCrypt Password Encryption
- JWT Token Authentication
- Stateless Session Management
- Spring Security Filter Chain
- Custom UserDetailsService
- Authentication Manager
- Authorization using Bearer Token

---

## 🚀 Future Improvements

- Role-Based Authorization (ADMIN / USER)
- Refresh Token Support
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Testing
- CI/CD Pipeline

---

## 👨‍💻 Author

**Shibananda Rout**

- GitHub: https://github.com/shibanandarout
- LinkedIn: https://www.linkedin.com/in/shibananda-rout