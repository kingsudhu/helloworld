FROM openjdk:8
ADD . /opt/
COPY target/example.smallest-0.0.1-SNAPSHOT.jar /opt/

EXPOSE 8085
ENTRYPOINT java -jar /opt/example.smallest-0.0.1-SNAPSHOT.jar
