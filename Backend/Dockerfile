# Stage 1: Build the Spring Boot application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /usr/app
COPY . /usr/app
RUN mvn package -DskipTests

# Stage 2: Create a lightweight image with just the JAR file
FROM openjdk:17.0.1-jdk-slim
WORKDIR /usr/app
COPY --from=build /usr/app/target/Application.jar /usr/app/
EXPOSE 8080
CMD ["java", "-jar", "Application.jar"]