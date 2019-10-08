FROM openjdk:8u181-jdk-alpine3.8
ADD @project.build.finalName@.jar /usr/local/

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/usr/local/@project.build.finalName@.jar"]