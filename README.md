================================Smart Inventory Management System=======================================

A production-style  Spring Boot microservices mono-repo  designed to model a real-world inventory management platform.  
This project demonstrates  clean architecture, service separation, shared libraries, security design, and backend problem-solving skills .

---

Project Goals

- Design a scalable inventory system using  microservices architecture 
- Apply  real-world backend design decisions 
- Showcase  professional GitHub practices 
- Demonstrate  problem-solving mindset beyond CRUD 

---
Architecture Overview

- Mono-repo containing multiple independent Spring Boot services
- Each microservice:
  - Owns its database schema
  - Exposes REST APIs
  - Follows layered architecture (Controller → Service → Repository)
- Shared functionality extracted into reusable modules

SmartInventoryManagementSystem
│
├── inventoryService
├── productService
├── orderService
├── userService
├── authService
├── common
└── common-security

---

Microservices Breakdown

 --inventoryService
- Core inventory domain logic
- Product stock management
- Business rules enforcement

 --productService
- Product catalog
- Product search and filtering APIs

 --orderService
- Order lifecycle management
- Order creation and status updates

 --userService
- User profile and role management
- User-related data persistence

 --authService
- Authentication service
- JWT token generation and validation

 --common
- Shared DTOs
- Common utilities and constants

 --common-security
- Centralized Spring Security configuration
- JWT helpers and filters
- Shared security components

---

 --Tech Stack

| Technology | Purpose |
|----------|--------|
| Java 17 | Language (project constraint) |
| Spring Boot | Microservice framework |
| Spring Data JPA | ORM & persistence |
| Hibernate | JPA provider |
| PostgreSQL | Database |
| Spring Security | Authentication & Authorization |
| Maven | Build & dependency management | Docker |

---

 --Security Design

- Centralized JWT-based authentication
- Stateless security model
- Shared security logic via `common-security` module
- Role-based access control ready for extension

---

 --Engineering & Problem-Solving Highlights

- Mono-repo structure with service isolation
- Avoided code duplication using shared libraries
- Clear domain boundaries per service
- Scalable design suitable for containerization
- Clean separation of concerns
- Designed with production-readiness in mind

---

 --How to Run (Local)

1. Clone the repository
```bash
git clone https://github.com/<your-username>/SmartInventoryManagementSystem.git
