FROM openjdk:jdk-slim as builder

RUN mkdir -p /build/
WORKDIR /build/

COPY . /build/

# Should fix StackOverflow errors
ARG MAVEN_OPTS="-Xms256m -Xmx1024m -Xss1024k"

# Install maven
RUN apt-get update &&  \
    apt-get install -y maven git

# Builds the launcher
RUN mvn package && \
    mv target/alternative-addervnf-1.0-SNAPSHOT-jar-with-dependencies.jar /stringaddervnf.jar

FROM java:8
ENV PORT=8080 STRING_TO_ADD=vnf
WORKDIR /
COPY --from=builder /stringaddervnf.jar /stringaddervnf.jar
EXPOSE $PORT
CMD java -jar stringaddervnf.jar $STRING_TO_ADD $PORT
