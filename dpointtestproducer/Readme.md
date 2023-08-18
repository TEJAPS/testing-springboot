# Spring Boot Employee Management API

This Spring Boot application serves as an example of an employee management system, providing APIs to manage employee data.

## Employee APIs

### Retrieve All Employees

Retrieves a list of all employees.

- **URL:** `/api/employees`
- **Method:** `GET`

### Create New Employee

Creates a new employee.

- **URL:** `/api/employees`
- **Method:** `POST`

## Swagger API Documentation

Explore and interact with the APIs using the Swagger UI documentation.

- **URL:** [Swagger UI](http://localhost:9001/swagger-ui/index.html)

## Actuator Endpoints

Monitor and manage application health, metrics, and more using the Actuator endpoints.

- **Health Check Endpoint:** `/actuator/health`
- **Other Actuator Endpoints:** Available as per Spring Boot Actuator documentation.

## Sample Data (For Development)

The application is configured to populate the database with sample data on startup. Here are a few sample employee records:

```sql
INSERT INTO employees (first_name, last_name, email) VALUES ('John', 'Doe', 'john.doe@example.com');
INSERT INTO employees (first_name, last_name, email) VALUES ('Jane', 'Smith', 'jane.smith@example.com');
INSERT INTO employees (first_name, last_name, email) VALUES ('Michael', 'Johnson', 'michael.johnson@example.com');
