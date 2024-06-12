
# Customer Notification System

## Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher
- Docker (optional, for database setup)

## Setting Up the Project

### Step 1: Clone the Repository

```sh
git clone https://github.com/giolatsa/customer-notification-system.git
cd customer-notification-system
```

### Step 2: Set Up PostgreSQL Database

#### Option 1: Using Docker

Ensure Docker is installed and running. Run the following command to start the PostgreSQL database:

```sh
docker-compose up -d
```

#### Option 2: Manual Setup

Install PostgreSQL and create the database manually:

```sh
psql -U postgres
CREATE DATABASE crocobet;
CREATE USER postgres WITH PASSWORD 'test123';
GRANT ALL PRIVILEGES ON DATABASE croco TO postgres;
```

### Step 3: Configure Application Properties

Ensure the `application.properties` file is set up with the correct database connection details:
####  PORT 5433

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/crocobet
spring.datasource.username=postgres
spring.datasource.password=test123
```

### Step 4: Run the Application

Use Maven to build and run the application:

```sh
mvnw clean install
mvnw spring-boot:run
```

### Step 5: Access the Application

#### Default Admin user credentials
- Username: admin
- Password: admin123

The application should now be running at `http://localhost:8080`. Access the Swagger UI for API documentation at `http://localhost:8080/swagger-ui`.

