# 🏗️ **Stage 1: Build the Application**
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# 🏗️ **Stage 2: Create the Final Runtime Image**
FROM openjdk:17
WORKDIR /app

# Copy only the built JAR from the first stage
COPY --from=build /app/target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

# Expose the application port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "product-service.jar"]
