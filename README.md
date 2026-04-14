# 💰 Expense Tracker API

Backend API built with **Java, Spring Boot, MySQL and Docker** for managing personal expenses.

---

## 🚀 Features

- Create, read and filter expenses
- RESTful API with clean architecture
- DTO-based design (no entity exposure)
- Global API response structure
- Input validation & exception handling
- Swagger documentation
- Dockerized application (backend + MySQL)
- Unit testing with JUnit + Mockito

---

## 🏗️ Tech Stack

- Java 21+
- Spring Boot
- Spring Data JPA
- MySQL
- Docker & Docker Compose
- Maven
- Swagger (OpenAPI)
- JUnit + Mockito

---

## 📦 Architecture

Layered architecture:

Includes:

- DTOs for request/response
- Mapper layer for separation of concerns
- Global exception handling

---

## ⚙️ How to Run

### 1. Clone repo

```bash
git clone <repo-url>
docker-compose up --build
```

### 2. Acces API

http://localhost:8080

### 3. Swagger

http://localhost:8080/swagger-ui/index.html

### 4. Example API

POST /api/expenses

```JSON
{
  "amount": 25.50,
  "category": "food",
  "description": "pizza",
  "date": "2026-04-13"
}
```

## ⚙🏗️ Key Design Decisions

- DTOs used to decouple API from database
- Service layer isolates business logic
- Global response wrapper for consistency
- Docker for reproducibility across environments