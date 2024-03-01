ARG JDK_VERSION=17
FROM openjdk:${JDK_VERSION}-jdk-slim
ARG JAR=/build/libs/template-project.jar
ARG EXPOSE=8080
ENV EXPOSE=${EXPOSE}

EXPOSE ${EXPOSE}

COPY ${JAR} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]