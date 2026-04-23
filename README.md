# Smart Lost & Found System

A Spring Boot REST API that allows users to report, track, and manage lost and found items efficiently.

---

## Overview

This project solves a real-world problem where users can report lost or found items. It supports CRUD operations, optimized searching, and clean architecture using Spring Boot.

---

## Features

- Create, Read, Update, Delete (CRUD)
- DTO pattern for clean API design
- Input validation using @Valid
- Global exception handling
- Search by location using HashMap (DSA)
- Sorting for latest items
- Claim item feature (status update)

---

## Tech Stack

- Java  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Maven  
- Postman  

---

## Project Structure

com.vikas.lostfound
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── enums

---

## API Endpoints

POST /items → Create item  
GET /items → Get all items  
PUT /items/{id} → Update item  
DELETE /items/{id} → Delete item  
GET /items/search?location= → Search by location  
PUT /items/{id}/claim → Mark as claimed  

---

## How to Run

1. Clone the repository  
git clone https://github.com/Vikasgabale9/lostfound-tracker.git  

2. Configure database in application.properties  

3. Run the application  
mvn spring-boot:run  

---

## Learning Outcomes

- Built REST APIs using Spring Boot  
- Applied layered architecture  
- Used DTO for data transfer  
- Implemented exception handling  
- Applied DSA concepts (HashMap, Sorting)  

---

## Author

Vikas Gabale  
GitHub: https://github.com/Vikasgabale9  

## Future Enhancements

The following features can be added to improve the project further:

### Swagger API Documentation
- Integrate Swagger (OpenAPI) to provide interactive API documentation  
- Helps in testing APIs directly from browser  
- Improves developer experience  

---

### Logging System
- Implement logging using SLF4J / Logback  
- Track API requests and responses  
- Helps in debugging and monitoring  

---

### Pagination Support
- Implement pagination for large datasets  
- Improves performance and response time  
- Example: fetching items page-wise instead of all at once  
