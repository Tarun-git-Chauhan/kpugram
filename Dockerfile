# Start from an OpenJDK image with Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy pom and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy everything and build the app
COPY . .
RUN mvn clean install

# Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/test3-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
