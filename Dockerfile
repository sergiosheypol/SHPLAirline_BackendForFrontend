FROM openjdk:11
COPY target/locations-0.0.1-SNAPSHOT.jar .
CMD java -jar locations-0.0.1-SNAPSHOT.jar
EXPOSE 80