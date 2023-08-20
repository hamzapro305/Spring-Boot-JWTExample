FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY ./target/Application.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "Application.jar"]