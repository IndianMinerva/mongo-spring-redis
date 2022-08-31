FROM openjdk:11.0.14-jre
VOLUME /opt
COPY target/mainModule-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
