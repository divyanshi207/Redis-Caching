# Redis Caching with Spring Boot

## Overview

This project demonstrates the implementation of Redis caching in a Spring Boot application to improve application performance by reducing database calls. User data is cached in Redis, allowing faster retrieval of frequently accessed records.

## Features

* Spring Boot REST APIs
* Redis Cache Integration
* CRUD Operations for User Management
* Cacheable User Retrieval
* Automatic Cache Updates
* MySQL Database Integration
* Spring Data JPA & Hibernate

## Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* Spring Cache
* Redis
* MySQL
* Maven
* Hibernate

## Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── config
└── resources
```

## Cache Flow

1. User data is fetched using REST APIs.
2. On the first request, data is retrieved from MySQL and stored in Redis.
3. Subsequent requests are served directly from Redis.
4. When user data is updated, the cache is refreshed automatically.

## API Endpoints

### Add User

```http
POST /users
```

### Get User By Id

```http
GET /cache/get-user/{id}
```

### Update User

```http
PUT /cache/update-users/{id}
```

### Delete User

```http
DELETE /cache/remove-users/{id}
```

## Redis Configuration

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.type=redis
```

## Running the Application

### Start Redis

```bash
redis-server
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

## Benefits of Redis Caching

* Reduces database load
* Improves response time
* Enhances application scalability
* Improves user experience

## Author

Divyanshi Agrawal
Backend Developer | Java | Spring Boot | Redis
