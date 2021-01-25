FROM openjdk:11.0-jdk-slim

EXPOSE 8082

ARG JAR_FILE=target/aposentadoria-caixa-api-*.jar

WORKDIR /opt/app

COPY ${JAR_FILE} aposentadoria-caixa-api.jar

ENTRYPOINT ["java","-jar","aposentadoria-caixa-api.jar"]