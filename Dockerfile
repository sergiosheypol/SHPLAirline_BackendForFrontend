FROM openjdk:11
COPY target/bff-0.0.1-SNAPSHOT.jar .
CMD java -jar bff-0.0.1-SNAPSHOT.jar
EXPOSE 80