FROM openjdk:17-jdk-alpine
COPY target/travsky-0.0.1-SNAPSHOT.jar travsky-app.jar
expose 8080  
ENTRYPOINT ["java", "-jar", "travsky-app.jar"]

CMD ["/bin/sh"]