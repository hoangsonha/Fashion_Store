#FROM maven:3.8.4-openjdk-17-slim AS build
#
##create folder app, if dont have app, the root will be default
#WORKDIR /app
#
##Copy into /app
#COPY pom.xml .
#COPY src ./src
#
##Build the application
#RUN mvn clean package
#
#
#FROM openjdk:20-jdk-slim
#
#ARG JAR_FILE=target/*.jar
#
#COPY --from=build /app/${JAR_FILE} app.jar
#
#ENTRYPOINT ["java","-jar","/app.jar"]
#
##WORKDIR /app
##COPY --from=build /app/target/*.jar /app/app.jar
##EXPOSE 8080
##CMD ["java", "-jar", "/app/app.jar"]

FROM amazoncorretto:22

WORKDIR /app

COPY target/Fashion_Store-0.0.1-SNAPSHOT.jar /app/demone.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "demone.jar"]