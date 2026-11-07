# ---- Stage 1: Build the JAR ----
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Stage 2: Run the JAR ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# 1. Install timezone data and set timezone to Lagos
RUN apt-get update && apt-get install -y tzdata && rm -rf /var/lib/apt/lists/*
ENV TZ=Africa/Lagos

# 2. Create a config directory
RUN mkdir -p /app/config

# 3. Copy the built jar
COPY --from=builder /build/target/*.jar app.jar

# 4. Copy the application.properties to the external config folder
COPY --from=builder /build/src/main/resources/application.properties /app/config/application.properties
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]