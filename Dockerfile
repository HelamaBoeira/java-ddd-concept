FROM maven:3.8.1-openjdk-17 as build
WORKDIR usr/src/app

COPY src ./src
COPY pom.xml .
RUN mvn clean package


FROM openjdk:17-alpine
WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/*.war ./app.war

ENTRYPOINT ["java", "-jar", "./app.war"]

EXPOSE 8080