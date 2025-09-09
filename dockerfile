# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests clean package

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app
EXPOSE 8080
# copy the (only) fat jar from target
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
