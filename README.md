# 💸 Expense Tracker API

Backend RESTful para la gestión de gastos personales y compartidos, inspirado en aplicaciones como Splitwise.

---

## 🚀 Features

* Registro y autenticación de usuarios (JWT)
* Creación de gastos personales
* Creación de grupos de gastos
* División de gastos entre múltiples usuarios
* Cálculo de balances entre usuarios
* API documentada con Swagger
* Manejo global de errores
* Tests unitarios con JUnit y Mockito

---

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas:

```
controller → service → repository → database
```

### 📦 Capas

* **Controller** → expone endpoints REST
* **Service** → lógica de negocio
* **Repository** → acceso a datos (JPA)
* **Model** → entidades del dominio
* **DTO** → contratos de entrada/salida
* **Mapper** → conversión entre entidades y DTOs

---

## 🧠 Decisiones de diseño

* Uso de **DTOs** para desacoplar la API del modelo interno
* Separación clara de responsabilidades (SOLID)
* Uso de **Enums** para consistencia del dominio
* Manejo centralizado de errores con `@RestControllerAdvice`
* Tests unitarios enfocados en lógica de negocio
* Contenerización con Docker para entorno reproducible

---

## 🛠️ Tecnologías

* Java 21
* Spring Boot 3
* Spring Data JPA
* Spring Security
* MySQL (Docker)
* JWT (io.jsonwebtoken)
* Swagger (OpenAPI)
* Maven
* JUnit + Mockito

---

## 🐳 Setup con Docker

### 1. Levantar base de datos

```bash
docker-compose up -d
```

### 2. Ejecutar aplicación

```bash
mvn spring-boot:run
```

---

## 📖 API Documentation

Disponible en:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔑 Endpoints principales

### Auth

```
POST /api/auth/register
POST /api/auth/login
```

---

### Expenses

```
POST /api/expenses
GET /api/expenses
GET /api/expenses/user/{userId}
GET /api/expenses/group/{groupId}
```

---

### Split Expenses

```
POST /api/expenses/split
```

---

### Groups

```
POST /api/groups
GET /api/groups
```

---

### Balances

```
GET /api/balances/group/{groupId}/net
```

---

## 🧪 Testing

```bash
mvn test
```

Incluye tests para:

* ExpenseService
* ExpenseShareService
* JwtService
* BalanceService

---

## 📊 Ejemplo de flujo

1. Crear usuario
2. Crear grupo
3. Añadir gasto compartido
4. Dividir gasto
5. Consultar balances

---

## 👤 Autor: Iñaki Ramos Iturria

Desarrollado como proyecto de portfolio enfocado en backend engineering.