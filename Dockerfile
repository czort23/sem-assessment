FROM openjdk:17-jdk-slim
COPY ./target/sem-assessment.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-assessment.jar"]