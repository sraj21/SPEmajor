
FROM openjdk:11
COPY ./target/SPE-mini-project-1.0-SNAPSHOT.jar ./
WORKDIR ./
CMD ["java","-jar","SPE-mini-project-1.0-SNAPSHOT.jar"]
