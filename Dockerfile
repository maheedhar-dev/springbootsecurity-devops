#---------STAGE 1: BUILD---------
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

#Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

#---------STAGE 2: RUN---------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]