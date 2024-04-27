# EHR Plugin

This is a Spring Boot application for an Electronic Health Record (EHR) plugin.

## Prerequisites

Before you begin, ensure you have the following installed:

- JDK 17 or later
- Maven
- Docker
- Docker Compose

## Installation

1. **Clone this repository:**

   ```bash
   git clone https://github.com/Johirul-Amin-Rizve/ehr-plugin.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd ehr-plugin
   ```

3. **Run PostgreSQL using Docker Compose:**

   ```bash
   docker-compose -f docker/docker-compose.yml up -d
   ```

## Build and Run

1. **Build the project:**

   ```bash
   mvn clean install -DskipTests
   ```

2. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

3. **Access the application at** [http://localhost:8080](http://localhost:8080)

## Production Deployment with Docker

To deploy the EHR plugin application in a production environment using Docker, follow these steps:
   
1. **Build the Docker image:**

   ```bash
   docker build -t ehr-plugin:latest .

2. **Run the Docker image:**

    ```bash
    docker run -p 8080:8080 --network docker_default -e "SPRING_PROFILES_ACTIVE=prod" ehr-plugin:latest

## Additional Notes

- This project uses Spring Boot, Spring Data JPA, Spring Security, and PostgreSQL.
- Lombok is included as an optional dependency for convenient boilerplate code generation.
- The Docker Compose file is provided in the `docker` directory to set up PostgreSQL for local development.