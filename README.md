# 📦 courier-tracking-project

This project is a Spring Boot project that tracks the movement of couriers, calculates their total travel distance and records their location when they get close to stores.

## Technologies and Libraries
- **Java 23**
- **Spring Boot 3**
- **H2 Database**
- **Lombok**
- **Swagger**

## Endpoints and Example Requests

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
## Installation and Usage

1. Clone the repo
```sh
git clone https://github.com/username/courier-tracking.git
```

2. Load Dependencies
```sh
mvn clean install
```

3. Run
```sh
mvn spring-boot:run
```

## H2 Console
- **URL: http://localhost:8080/h2-console**
- **JDBC URL: jdbc:h2:mem:testdb**
- **User: sa**
- **Password:**

## Swagger

You can find the endpoints using the Swagger UI.

To access the Swagger UI, run the application and navigate to the following URL in your browser:

```powershell
http://localhost:8080/swagger-ui.html
```

