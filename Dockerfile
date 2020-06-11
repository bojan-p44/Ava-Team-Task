#FROM java:8
#COPY target/ava-0.0.1-SNAPSHOT.jar ava-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","ava-0.0.1-SNAPSHOT.jar"]

FROM maven:3.5-jdk-8
