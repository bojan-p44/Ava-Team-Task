FROM adoptopenjdk/openjdk14

ADD target/ava-1.0.jar ava-1.0.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","ava-1.0.jar"]