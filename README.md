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


## How I implemented Redis caching in Spring Boot

1:Added dependencies
Included Spring Cache and Spring Data Redis in pom.xml.

2:Enabled caching
Enabled caching at the configuration level.

3:Configured Redis
Configured Redis connection and cache settings in application.properties.

4:Used cache annotations
Used cache annotations on service methods.

## What is TTL?

TTL (Time To Live) is the expiration time for a cache entry.
Example: If TTL is 2 minutes, the cached value is automatically removed from Redis after 10 minutes.
Why use TTL?
Prevents stale data from remaining forever.
Controls memory usage.
Ensures periodic refresh from the database.

## @Cacheable
Purpose: Read-through caching.
Flow:
Check Redis for key users::1.
If present → return cached value.
If absent → execute method, fetch from DB, store in Redis, and return result.
This is used for read operations.

## @CachePut
Purpose: Update cache whenever the method executes.
Unlike @Cacheable, the method always runs.
Flow:
Save user in DB.
Store the returned User object in Redis.
Cache stays synchronized with the latest data.
This is commonly used for create/update operations.

## @CacheEvict
Purpose: Remove data from cache.
Flow:
Delete from DB.
Remove corresponding cache entry.
Prevents stale cached data.
Can also use allEntries = true to clear an entire cache.

## RedisCacheManager
Purpose: Spring component that manages caches backed by Redis.
It:
Creates cache regions (e.g., users).
Applies TTL settings.
Handles serialization/deserialization.
Communicates with Redis.
If you don’t define it manually, Spring Boot can auto-configure it when spring.cache.type=redis is set.

## Serialization (Important)
Initially you saw binary data and NullValue because Java serialization was being used.
To store readable JSON:
This stores objects as JSON in Redis instead of binary bytes.

## Simple Explaination
I implemented Redis caching in a Spring Boot application using Spring Cache. I enabled caching with @EnableCaching and configured Redis through Spring Data Redis. For read operations I used @Cacheable, which first checks Redis and only hits the database on a cache miss. For create/update operations I used @CachePut so the cache is refreshed with the latest User object, and for delete operations I used @CacheEvict to remove stale entries. I configured a TTL of 10 minutes so cached data expires automatically, and I used JSON serialization to store readable data in Redis.”


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
docker compose up -d
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
