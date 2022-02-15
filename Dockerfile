FROM openjdk:11
COPY app/build/libs/app.jar project1.jar
CMD ["java", "-jar", "project1.jar"]
