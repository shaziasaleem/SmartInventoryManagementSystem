# Inventory Management Microservices

## Project Overview
This repository contains a mono-repo of Spring Boot microservices for an inventory management system.

## Architecture
- Each service is a separate Spring Boot application following a microservice pattern.
- Services communicate via REST APIs and use PostgreSQL for persistence.
- Shared libraries are provided under `common` and `common-security`.

## Microservices
- `inventoryService` — Core inventory management (products CRUD, business rules).
- `productService` — Product catalog and search.
- `orderService` — Order processing and lifecycle.
- `userService` — User management and authentication data.
- `authService` — Authentication and JWT issuing.
- `common` — Shared utilities and DTOs.
- `common-security` — Shared security configuration and JWT helpers.

## Tech Stack
- Java 8 (project constraint)
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Spring Security
- Maven

## Build & Run Locally
1. Ensure you have Java and Maven installed.
2. Configure PostgreSQL and set `application.properties` for each service.
3. From repository root build all modules:

```bash
mvn -T 1C -DskipTests package
```

4. Run a single service (example):

```bash
cd inventoryService
mvn spring-boot:run
```

## GitHub
Repository: `inventory-management-microservices` (mono-repo)

## Notes
- Do not commit secrets — use environment variables or external config.
- `target/` directories are excluded via `.gitignore`.

