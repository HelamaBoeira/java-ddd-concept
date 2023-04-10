FROM maven:3.8.1-openjdk-17 as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:17-alpine
COPY --from=build /usr/src/app/target/*.war /usr/app/app.war

ENTRYPOINT ["java", "-jar", "/usr/app/app.war"]

EXPOSE 8080