# docker build -t local-config-server:0.0.1-stable .

FROM gradle:8.5-jdk21 AS builder
WORKDIR /workdir/server
COPY . /workdir/server
RUN gradle build --no-daemon -x test

FROM openjdk:21-jdk-slim
EXPOSE 8888
COPY --from=builder /workdir/server/build/libs/*.jar /app/spring-app.jar
ENTRYPOINT ["java", "-jar", "/app/spring-app.jar"]
