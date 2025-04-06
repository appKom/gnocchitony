FROM gradle:8-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar


FROM openjdk:17
USER nobody
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]