# Stage 1: Build the Ktor application
FROM gradle:jdk17-alpine AS build
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY src ./src
RUN gradle clean build

# Stage 2: Create the final runtime image
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]