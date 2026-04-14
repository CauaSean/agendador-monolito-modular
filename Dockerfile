FROM maven:3.8-openjdk-17 AS BUILD
WORKDIR /APP
COPY . .

RUN mvn clean install -DskipTest
FROM openjdk:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/target/*.jar  app.jar
EXPOSE 9000
CMD ["java", "-jar", "/app/tarefas.jar"]