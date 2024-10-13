# Ktor E School Backend

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Tech Stack](#tech-stack)
4. [Prerequisites](#prerequisites)
5. [Installation](#installation)
6. [Environment Variables](#environment-variables)
7. [Usage](#usage)
8. [API Documentation](#api-documentation)
9. [Testing](#testing)
10. [Deployment](#deployment)
11. [Contributing](#contributing)
12. [License](#license)
13. [Acknowledgments](#acknowledgments)

---

## Introduction
**E School** is a backend system built with **Ktor** to manage management operations efficiently. It integrates with **PostgreSQL** for persistent data storage and provides structured APIs for smooth interaction with frontends or external services.

---

## Features
- **User Authentication & Authorization** (e.g., JWT)
- **CRUD Operations** for managing products, orders, and users
- **Database Integration** with PostgreSQL
- **Exception Handling** using Ktor’s pipeline
- **Structured API Documentation** with Swagger
- **Unit and Integration Testing**

---

## Tech Stack
- **Language:** Kotlin
- **Framework:** Ktor
- **Database:** PostgreSQL
- **Build Tool:** Gradle
- **API Style:** REST / JSON

---

## Prerequisites
Make sure the following tools are installed on your machine:
- **Kotlin:** `>= 1.7.0`
- **Java:** `>= 17`
- **PostgreSQL:** `>= 16`


---

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/sazibislam/KtorSchoolManagementBackend.git
   cd e-school-backend
   ```

2. **Set up PostgreSQL:**
    - Create a new database:
      ```bash
      createdb e_school
      ```
    - Configure your database user and password.

3. **Install dependencies:**
   ```bash
   ./gradlew build
   ```

---

## Environment Variables
Create a `.env` file in the root directory and add the following:

```
DB_URL=jdbc:postgresql://localhost:5432/e_school
DB_USER=your_database_username
DB_PASSWORD=your_database_password
PORT=8080
JWT_SECRET=supersecretkey
```

---

## Usage
1. **Start the development server:**
   ```bash
   ./gradlew run
   ```

2. The server will be available at:
   ```
   http://localhost:8080
   ```

### Common Gradle Commands:
- **`./gradlew build`**: Build the project
- **`./gradlew run`**: Run the server locally
- **`./gradlew test`**: Run the test suite

---

## API Documentation

Please find the postman collection. Swagger is available at:
```
http://localhost:8080/swagger
```

### Example Endpoints:
- **GET /products** – Retrieve all products
- **POST /orders** – Create a new order
- **POST /auth/login** – User login

---

## Testing
Run tests using the following command:
```bash
./gradlew test
```

---

## Deployment
1. **Docker Deployment**:
    - Build and run using Docker:
      ```bash
      docker-compose up --build
      ```

2. **Heroku / AWS / GCP Deployment**:  
   Example for deploying to Heroku:
   ```bash
   git push heroku main
   ```

---

## Contributing
We welcome contributions!

1. **Fork the repository**
2. **Create a new branch:**
   ```bash
   git checkout -b feature-name
   ```  
3. **Make your changes and commit:**
   ```bash
   git commit -m "Added new feature"
   ```  
4. **Push to your fork and create a pull request**

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

---

## Acknowledgments
- **Ktor Documentation**: [https://ktor.io](https://ktor.io)
- **PostgreSQL Documentation**: [https://www.postgresql.org](https://www.postgresql.org)
- Special thanks to all contributors!
