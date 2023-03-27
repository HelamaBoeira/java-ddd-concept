FROM openjdk:17-alpine

ADD target/*.war app.war

ENTRYPOINT ["java", "-jar", "/app.war"]

EXPOSE 8080