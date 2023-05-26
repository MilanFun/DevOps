FROM ubuntu
COPY pom.xml pom.xml
RUN apt-get update && \
    apt-get install -y default-jre && \
    apt-get install -y default-jdk && \
    apt-get install -y maven && \
    mvn install
