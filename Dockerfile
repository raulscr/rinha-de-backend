FROM gradle:8.2.1-jdk17 AS build
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts /app/
COPY gradle /app/gradle
COPY src /app/src
RUN gradle build --no-daemon

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/application.jar
CMD ["java", "-jar", "/app/application.jar"]
