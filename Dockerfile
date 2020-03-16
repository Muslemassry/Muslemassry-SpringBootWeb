FROM openjdk:11
COPY /target/SpringBootWeb-0.0.1-SNAPSHOT.jar /usr/src/
WORKDIR /usr/src/
CMD ["java", "-Dspring.profiles.active=container",  "-jar", "SpringBootWeb-0.0.1-SNAPSHOT.jar"]
