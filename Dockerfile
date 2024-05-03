FROM gradle:8.6.0-jdk17-alpine

COPY . .

RUN gradle build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/Medical-Clinic-0.0.1-SNAPSHOT.jar"]