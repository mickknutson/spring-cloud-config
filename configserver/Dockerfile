##---------------------------------------------------------------------------##
## Dockerfile
##---------------------------------------------------------------------------##
# We're using the official OpenJDK image from the Docker Hub (https://hub.docker.com/_/openjdk/).
# Take a look at the available versions so you can specify the Java version you want to use.

#FROM java:openjdk-8-jdk
# assume latest:
FROM openjdk:8-jre-alpine

MAINTAINER Mick Knutson <mknutson@baselogic.io>

##---------------------------------------------------------------------------##
## ARG's
ARG APPLICATION_PATH=/app

##---------------------------------------------------------------------------##
## ENV Commands

# Can override:
ENV PORT=8888 \
    SPRING_OUTPUT_ANSI_ENABLED="ALWAYS" \
    JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap" \
    CLOUD_CONFIG_REPO="file:////app/microservices_config_repo"


##---------------------------------------------------------------------------##
# Set the WORKDIR. All following commands will be run in this directory.
WORKDIR ${APPLICATION_PATH}


##---------------------------------------------------------------------------##
#COPY ./build/libs/*-exec.jar /app/application-exec.jar
ADD ./build/libs/*-exec.jar ${APPLICATION_PATH}/application-exec.jar

##---------------------------------------------------------------------------##
ENV APK_ADD="bash curl httpie"
#ENV APK_ADD="bash httpie"
ENV APK_DEL="curl busybox httpie"

RUN apk update && \
    apk upgrade && \
    apk add --no-cache ${APK_ADD}

##---------------------------------------------------------------------------##
RUN rm -rf \
      /usr/share/man/* \
      /usr/includes/* \
      /var/cache/apk/* \
      /root/.npm/* \
      /usr/lib/node_modules/npm/man/* \
      /usr/lib/node_modules/npm/doc/* \
      /usr/lib/node_modules/npm/html/* \
      /usr/lib/node_modules/npm/scripts/*

##---------------------------------------------------------------------------##
# Which ports to expose to the outside host
# -p [HOST:CONT]
# 8080
EXPOSE $PORT


#//---------------------------------------------------------------------------//
# Application Entry Point:

ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./application-exec.jar

#ENTRYPOINT ["/usr/bin/java"]
#CMD [   "-Djava.security.egd=file:/dev/./urandom", \
#     "-jar", "./application-exec.jar" ]


##---------------------------------------------------------------------------##
## HEALTHCHECK's
#HEALTHCHECK --interval=2m --timeout=2s --retries=3 \
#            CMD curl -f http://0.0.0.0:$PORT/BOB

           # container:port/health --> JSON happy or not

# Spring Bot Actuator:
#HEALTHCHECK CMD curl -v --silent http://0.0.0.0:$PORT/health 2>&1


#RUN apk del ${APK_DEL}


##---------------------------------------------------------------------------##
##---------------------------------------------------------------------------##
