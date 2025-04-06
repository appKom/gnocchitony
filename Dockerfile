FROM gradle:7.4.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:17
EXPOSE 8080

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/autobank-image.jar /app/autobank-image.jar

COPY src/main/resources/application.properties.template /app/config/application.properties


RUN bash -c 'touch /app/autobank-image.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-Dspring.config.location=/app/config/application.properties","-jar","/app/autobank-image.jar"]

