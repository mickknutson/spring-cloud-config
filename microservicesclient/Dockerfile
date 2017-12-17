##---------------------------------------------------------------------------##
## Dockerfile
##---------------------------------------------------------------------------##
# We're using the official OpenJDK image from the Docker Hub (https://hub.docker.com/_/openjdk/).
# Take a look at the available versions so you can specify the Java version you want to use.
#FROM java:openjdk-8-jdk
FROM openjdk:8-jre-alpine
MAINTAINER Mick Knutson <mknutson@baselogic.io>

##---------------------------------------------------------------------------##
## ENV Commands

# Can override:
ENV PORT=8080 \
    SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    DELAY_SLEEP=0 \
    JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"

##---------------------------------------------------------------------------##
# Set the WORKDIR. All following commands will be run in this directory.
WORKDIR /app

##---------------------------------------------------------------------------##
#COPY ./build/libs/*-exec.jar /app/application-exec.jar
ADD ./build/libs/*-exec.jar /app/application-exec.jar


RUN apk update
RUN apk add curl
#RUN apk add --upgrade busybox


##---------------------------------------------------------------------------##
# Which ports to expose to the outside host
#EXPOSE 8080 8443 8888
#EXPOSE 8080 8443
EXPOSE $PORT


#//---------------------------------------------------------------------------//
# Application Entry Point:
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./application-exec.jar
#ENTRYPOINT ["/usr/bin/java"]
#CMD ["-Djava.security.egd=file:/dev/./urandom", "-jar", "./application-exec.jar"]


##---------------------------------------------------------------------------##

##---------------------------------------------------------------------------##
##---------------------------------------------------------------------------##
