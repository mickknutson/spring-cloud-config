##---------------------------------------------------------------------------##
## Dockerfile
##---------------------------------------------------------------------------##
# We're using the official OpenJDK image from the Docker Hub (https://hub.docker.com/_/openjdk/).
# Take a look at the available versions so you can specify the Java version you want to use.
#FROM java:openjdk-8-jdk
# assume latest:
FROM openjdk:8-jre-alpine

MAINTAINER Mick Knutson <mknutson@baselogic.io>

LABEL DESCRIPTION="This container is a Spring Cloud Configuration Client"

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
ENV PORT=8080 \
    SPRING_OUTPUT_ANSI_ENABLED="ALWAYS" \
    JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap" \
    USERNAME="spring" \
    USER_ID="1000"



##---------------------------------------------------------------------------##
# Set the WORKDIR. All following commands will be run in this directory.
WORKDIR ${APPLICATION_PATH}

##---------------------------------------------------------------------------##
## Copy all shell scripts
COPY ./*entrypoint.sh ${APPLICATION_PATH}/

ADD ./build/libs/*-exec.jar ${APPLICATION_PATH}/application-exec.jar

##---------------------------------------------------------------------------##
ENV APK_ADD="bash curl httpie"
#ENV APK_ADD="bash curl busybox httpie"
ENV APK_DEL="curl httpie"
#ENV APK_DEL="curl busybox httpie"

# NOTE: RUN is run when building the image NOT when running the container:
#RUN apk update && \
#    apk upgrade && \
#    apk add --no-cache ${APK_ADD}


##---------------------------------------------------------------------------##
# Clean up files not needed for running the container:
#RUN rm -rf \
#      /usr/share/man/* \
#      /usr/includes/* \
#      /var/cache/apk/* \
#      /root/.npm/* \
#      /usr/lib/node_modules/npm/man/* \
#      /usr/lib/node_modules/npm/doc/* \
#      /usr/lib/node_modules/npm/html/* \
#      /usr/lib/node_modules/npm/scripts/*

#RUN apk del ${APK_DEL}
#CMD apk del ${APK_DEL}

## Option to externalize the OS commands:
COPY ./*container-start.sh ${APPLICATION_PATH}/
RUN chmod -v +x ./container-start.sh && \
    ./container-start.sh && \
    rm ./container-start.sh

# Now create a new USER and make them the owner of our application files:
RUN adduser -H -D ${USERNAME} -u ${USER_ID} && \
    chown ${USERNAME}:${USERNAME} -R ${APPLICATION_PATH}/

##---------------------------------------------------------------------------##
# We should not run the application as root !
#USER spring
USER ${USERNAME}

##---------------------------------------------------------------------------##
# NOTE: RUN is run when building the image NOT when running the container:
# NOTE: CMD is run when building the container NOT when creating the image:
RUN echo "$NOTE_RUN"
CMD echo "$NOTE_CMD"


##---------------------------------------------------------------------------##
# Which ports to expose to the outside host
# -p [HOST:CONTAINER]
EXPOSE $PORT


##---------------------------------------------------------------------------##
# Entry Point Script:
#ENTRYPOINT ./client-entrypoint.sh

# Application Executable Entry Point:
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar ./application-exec.jar

# Application Executable Entry Point separate commands:
#ENTRYPOINT ["/usr/bin/java"]
#CMD ["-Djava.security.egd=file:/dev/./urandom", "-jar", "./application-exec.jar"]

##---------------------------------------------------------------------------##
## HEALTHCHECK's
# http://container:port/health --> JSON happy or not
# docker inspect --format "{{json .State.Health.Status }}" microservicesclient

ARG HEALTHCHECK_URI=http://0.0.0.0:$PORT/health

# TODO: Could use ARG's for retry parameters:
#HEALTHCHECK --interval=5m --timeout=2s --retries=3 CMD curl -f $HEALTHCHECK_URI

# Spring Boot Actuator: TODO: Revisit syntax:
HEALTHCHECK --interval=5m --timeout=2s --retries=3 CMD curl -v --silent HEALTHCHECK_URI 2>&1

##---------------------------------------------------------------------------##
##---------------------------------------------------------------------------##
