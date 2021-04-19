FROM adoptopenjdk/openjdk11-openj9
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transactions/app.jar
COPY environment/entrypoint.sh transactions/entrypoint.sh
ENTRYPOINT transactions/entrypoint.sh