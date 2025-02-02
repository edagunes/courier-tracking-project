# 📦 courier-tracking-project

This project is a Spring Boot project that tracks the movement of couriers, calculates their total travel distance and records their location when they get close to stores.

## Technologies and Libraries
- **Java 23**
- **Spring Boot 3**
- **H2 Database**
- **Lombok**
- **Swagger**

## Endpoints and Requests

### Save Courier If Nearby and Total Distance
```http
POST /api/courier-tracking/courier/location
```

### Get Total Distance
```http
GET /api/courier-tracking/courier/{courierId}/distance
Content-Type: application/json

{
    "courierId": 1
}
```

