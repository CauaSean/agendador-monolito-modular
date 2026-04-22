FROM gradle:8-jdk17-alpine AS build
WORKDIR /app
COPY . .

RUN gradle bootJar -x test
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]