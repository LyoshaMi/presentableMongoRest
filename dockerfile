FROM openjdk:17
EXPOSE 8080
VOLUME /tmp
ADD target/springboot-mongo-docker.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]