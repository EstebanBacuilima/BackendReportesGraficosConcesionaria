FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/java_app-0.0.1.jar
COPY ${JAR_FILE} java_app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","java_app.jar"]