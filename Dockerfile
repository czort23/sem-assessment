FROM eclipse-temurin:latest
COPY ./target/sem-assessment.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem-assessment.jar"]