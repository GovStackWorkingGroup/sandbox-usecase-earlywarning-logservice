FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN echo "Current WORKDIR:" && pwd && ls -la
RUN mvn clean install -U -DskipTests=true

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/log-service-0.0.1-SNAPSHOT.jar /app/log-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/app/log-service-0.0.1-SNAPSHOT.jar"]
