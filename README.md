Smart Food Ordering API 🍔🚀

A scalable backend REST API for an online food ordering platform inspired by modern food delivery systems like Swiggy and Zomato.
Built using Java, Spring Boot, Spring Security, JWT Authentication, JPA/Hibernate, MySQL, and MapStruct following industry-level layered architecture.

📌 Project Overview

The Smart Food Ordering API allows users to:

Register and authenticate securely using JWT
Browse restaurants and menus
Search/filter restaurants
Add food items to restaurants
Manage food availability
Place and manage orders
Track order status
Role-based access control for ADMIN and USER

This project is designed with clean architecture and scalable backend practices.

🛠️ Tech Stack

Backend

Java 21
Spring Boot
Spring Security
Spring Data JPA
Hibernate
JWT Authentication
MapStruct
Maven

Database

MySQL

Documentation & Testing

Swagger / OpenAPI
Postman

📂 Project Architecture

Controller Layer
↓
Service Layer
↓
Repository Layer
↓
Database

The project follows:

DTO Pattern
Layered Architecture
Exception Handling
Specification API for dynamic filtering
Role-Based Authorization
RESTful API standards

🔐 Authentication & Authorization

Authentication is implemented using JWT Tokens.

Roles
ADMIN

Can:

Add restaurants
Add food items
Toggle food availability
Manage restaurant status
USER

Can:

Browse restaurants
View menus
Add items to cart
Place orders
Track orders

📦 Modules Implemented

1️⃣ Authentication Module

Features

User Registration
Login Authentication
JWT Token Generation
Role-based Security

Endpoints

Register User
POST /api/auth/register

Login User
POST /api/auth/login

2️⃣ Restaurant Module

Features

Add Restaurant
Get All Restaurants
Search Restaurants
Filter by Category
Filter by City
Get Restaurant by ID
View Restaurant Menu
Deactivate Restaurant

Endpoints

Add Restaurant
POST /api/restaurants/add

Get Restaurants
GET /api/restaurants/get

Get Restaurant By ID
GET /api/restaurants/{id}

View Restaurant Menu
GET /api/restaurants/{id}/menu

3️⃣ Food Module

Features

Add Food to Restaurant
Get Food Items
Food Availability Toggle
Veg/Non-Veg Support
Food Category Support

Endpoints

Add Food Under Restaurant
POST /api/restaurants/{restaurantId}/foods

Get All Foods
GET /api/foods

Toggle Food Availability
PATCH /api/foods/{foodId}/availability

4️⃣ Cart Module

Features

Add to Cart
Remove from Cart
Update Quantity
View Cart

5️⃣ Order Module

Features

Place Order
Order History
Filter Orders by Status
Order Status Tracking

Endpoints

Place Order
POST /api/orders/place

User Orders
GET /api/orders/my-orders

🧠 Advanced Concepts Used

Spring Security Authentication
JWT Token Validation
JPA Relationships
Dynamic Query Filtering using Specifications
DTO Mapping using MapStruct
Pagination & Sorting
Exception Handling
Layered Architecture
REST API Best Practices

🗂️ Database Relationships

Restaurant ↔ FoodItem
One Restaurant → Many Food Items
User ↔ Orders
One User → Many Orders
Order ↔ OrderItems
One Order → Many Order Items

📖 Swagger Documentation

Swagger/OpenAPI is integrated for API testing and documentation.

Swagger URL
http://localhost:8084/swagger-ui/index.html

⚙️ Setup Instructions

1️⃣ Clone Repository

git clone https://github.com/Saijdhav45/Smart-food-ordering-REST_API.git

2️⃣ Configure Database

Create MySQL database:

CREATE DATABASE smart_food_ordering;
3️⃣ Configure Application Properties

Create:

application.properties

Add:

spring.datasource.url=jdbc:mysql://localhost:3306/smart_food_ordering
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key

4️⃣ Run Application

Run using:

mvn spring-boot:run

or run the main class from STS/IntelliJ.

🧪 API Testing

Test APIs using:

Swagger UI
Postman

📌 Future Enhancements
Payment Gateway Integration

Redis Caching
Docker Deployment
AWS Deployment
Microservices Architecture
Spring Cloud Gateway
Notification Service
Recommendation Engine
Spring AI Integration

📸 Sample Features

✅ JWT Authentication
✅ Restaurant Search
✅ Dynamic Filtering
✅ Pagination & Sorting
✅ Role-Based Authorization
✅ Swagger Documentation
✅ Food Availability Toggle

👨‍💻 Author

Sainath Jadhav

Java Backend Developer

⭐ If You Like This Project

Give this repository a star ⭐ on GitHub.