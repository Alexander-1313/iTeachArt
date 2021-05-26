FROM openjdk:11

COPY target/iteachart-0.0.1-SNAPSHOT.jar /demo.jar

CMD ["java", "-jar", "/demo.jar"]