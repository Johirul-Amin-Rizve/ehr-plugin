# Stage 1: Build Maven project
FROM maven:3.8.8-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copy the Maven project file
COPY pom.xml .

# Copy the entire project and build
COPY . .

# Build the Maven project with skipping tests
RUN mvn clean install -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Create logs directory
RUN mkdir -p /app/src/main/resources/logs

# Expose the port your application runs on
EXPOSE 8080

# Copy the packaged jar file from the previous stage
COPY --from=builder /build/target/ehr-plugin-0.0.1-SNAPSHOT.jar app.jar

# Command to run your application
CMD ["java", "-jar", "app.jar"]
