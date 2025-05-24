# Student Department Management System

A Spring Boot-based RESTful web application designed to manage student and department data with full CRUD operations. This system demonstrates backend development best practices, entity relationships, exception handling, and API testing.

## 🔧 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **RESTful APIs**
- **Postman**
- **Maven**

## 📌 Features

- CRUD operations for Students and Departments
- One-to-Many relationship between Department and Students
- Global exception handling using `@ControllerAdvice` and custom exception classes
- Input validation for names (alphabetic only)
- Structured JSON responses for both success and error cases
- API testing using Postman

## 🔄 API Endpoints

### Department Endpoints

- `POST /department`  
  → Add a new department (validates name; rejects special characters)  
- `GET /department`  
  → Retrieve all departments (throws `NullTableException` if empty)  
- `GET /department/{id}`  
  → Retrieve a department by ID (throws `DepartmentIdNotFoundException` if not found)  

### Student Endpoints (Within Departments)

- `POST /department/{id}/student-value`  
  → Add a new student to a department (validates name; links via `department_Id`)  
- `GET /department/{id}/student-value`  
  → Retrieve all students associated with a department  

### General Student Endpoints

- `GET /students`  
  → Retrieve all student records (throws `NullTableException` if table is empty)  

## 🗃️ Database Schema

### Department

| Field             | Type     | Description                                |
|------------------|----------|--------------------------------------------|
| `department_Id`   | Integer  | Primary Key                                |
| `department_Name` | String   | Validated to contain only alphabetic chars |
| `location`        | String   | Department's physical location             |
| `student`         | List<Student> | One-to-Many mapped list of students   |

### Student

| Field          | Type     | Description                                |
|----------------|----------|--------------------------------------------|
| `student_id`   | Integer  | Primary Key                                |
| `student_Name` | String   | Validated to contain only alphabetic chars |
| `age`          | Integer  | Age of the student                         |
| `gender`       | String   | Gender of the student                      |
| `department_Id`| Integer  | Foreign Key mapped to `Department`         |

## ⚠️ Exception Handling

Handled using a centralized global exception handler:

- `@ControllerAdvice` for global exception mapping
- `@ExceptionHandler` for custom exceptions:
  - `DepartmentIdNotFoundException`
  - `NullTableException`
  - `SpecialCharacterFoundException`
- Returns meaningful HTTP status codes and error messages

## 🧪 Testing

All REST endpoints were tested using **Postman** to validate:
- Functional correctness (valid/invalid inputs)
- Response formats
- HTTP status codes and exception flow
