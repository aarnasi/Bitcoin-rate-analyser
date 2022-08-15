FROM openjdk:11-jre-slim

RUN mkdir /app

COPY build/libs/*.jar /app/bitoin-rate-fetcher.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/bitoin-rate-fetcher.jar"]