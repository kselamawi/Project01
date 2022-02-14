FROM openjdk:8-jdk-alpine
COPY app/build/libs/app.jar project1.jar
CMD ["java", "-jar", "project1.jar"]
