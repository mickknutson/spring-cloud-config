##---------------------------------------------------------------------------##
## Dockerfile
##---------------------------------------------------------------------------##
# We're using the official OpenJDK image from the Docker Hub (https://hub.docker.com/_/openjdk/).
# Take a look at the available versions so you can specify the Java version you want to use.
#FROM java:openjdk-8-jdk
# assume latest:
FROM openjdk:8-jre-alpine

MAINTAINER Mick Knutson <mknutson@baselogic.io>

LABEL DESCRIPTION="This container is a Spring Cloud Configuration Server"
#USER nobody

##---------------------------------------------------------------------------##
## ARG's
# NOTE: ARG's are available in image create but NOT when running a container

ARG APPLICATION_PATH=/app

ARG NOTE_RUN="# NOTE: RUN is run when building the image NOT when running the container"
ARG NOTE_CMD="# NOTE: CMD is run when building the container NOT when creating the image"


##---------------------------------------------------------------------------##
## ENV Commands
# NOTE: ENV's are available in image create and running a container
# Can override these values:
ENV PORT=8888 \
ENV SPRING_OUTPUT_ANSI_ENABLED="ALWAYS" \
ENV JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap" \
ENV CLOUD_CONFIG_REPO="file:////app/microservices_config_repo" \
ENV USERNAME="spring" \
ENV USER_ID="1000"



##---------------------------------------------------------------------------##
# NOTE: Set the WORKDIR. All following commands will be run in this directory.
WORKDIR ${APPLICATION_PATH}

##---------------------------------------------------------------------------##
## Copy all shell scripts
COPY ./*entrypoint.sh ${APPLICATION_PATH}/

ADD ./build/libs/*-exec.jar ${APPLICATION_PATH}/application-exec.jar

##---------------------------------------------------------------------------##
ENV APK_ADD="bash curl httpie"
ENV APK_DEL="curl httpie"

# NOTE: RUN is run when building the image NOT when running the container:
RUN apk update
RUN apk upgrade
RUN apk add --no-cache ${APK_ADD}


##---------------------------------------------------------------------------##
# NOTE: Clean up files not needed for running the container:
RUN rm -rf
RUN /usr/share/man/*
RUN /usr/includes/*
RUN /var/cache/apk/*
RUN /root/.npm/*
RUN /usr/lib/node_modules/npm/man/*
RUN /usr/lib/node_modules/npm/doc/*
RUN /usr/lib/node_modules/npm/html/*
RUN /usr/lib/node_modules/npm/scripts/*

#RUN apk del ${APK_DEL}


# Now create a new USER and make them the owner of our application files:
RUN adduser -H -D ${USERNAME} -u ${USER_ID}

RUN chown ${USERNAME}:${USERNAME} -R ${APPLICATION_PATH}/

##---------------------------------------------------------------------------##
# NOTE: We should not run the application as root !
USER ${USERNAME}

##---------------------------------------------------------------------------##
# NOTE: RUN is run when building the image NOT when running the container:
RUN echo "$NOTE_RUN"


##---------------------------------------------------------------------------##
# Which ports to expose to the outside host
# -p [HOST:CONTAINER]
EXPOSE $PORT


##---------------------------------------------------------------------------##
# NOTE: Entry Points:
# Entry Point Script:
ENTRYPOINT ./entrypoint.sh

# Application Executable Entry Point:
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./application-exec.jar

# Application Executable Entry Point separate commands:
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "./application-exec.jar"]

##---------------------------------------------------------------------------##
# NOTE: Container HEALTHCHECK's
#
# To verify status:
# http://container:port/health --> JSON happy or not
# docker inspect --format "{{json .State.Health.Status }}" microservicesclient

ARG HEALTHCHECK_URI=http://0.0.0.0:$PORT/health

# Spring Boot Actuator: TODO: Revisit syntax:
HEALTHCHECK --interval=5m --timeout=2s --retries=3 CMD curl -v --silent $HEALTHCHECK_URI 2>&1

##---------------------------------------------------------------------------##
##---------------------------------------------------------------------------##
