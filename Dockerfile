FROM openjdk:17-alpine

ADD target/demo-0.8.war app.war

ENTRYPOINT ["java", "-jar", "/app.war"]

EXPOSE 8080
