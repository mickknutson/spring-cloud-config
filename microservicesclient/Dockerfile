##---------------------------------------------------------------------------##
## Dockerfile
##---------------------------------------------------------------------------##
# We're using the official OpenJDK image from the Docker Hub (https://hub.docker.com/_/openjdk/).
# Take a look at the available versions so you can specify the Java version you want to use.
#FROM java:openjdk-8-jdk
# assume latest:
FROM openjdk:8-jre-alpine

MAINTAINER Mick Knutson <mknutson@baselogic.io>

LABEL DESCRIPTION="This container is a Microservices Client leveraging Spring Cloud Configuration Server"
#USER nobody

##---------------------------------------------------------------------------##
## ARG's
# NOTE: ARG's are available in image create but NOT when running a container

ARG APPLICATION_PATH=/app

ARG build_number=1.0-SNAPSHOT

ARG NOTE_RUN="# NOTE: RUN is run when building the image NOT when running the container"


##---------------------------------------------------------------------------##
## ENV Commands
# NOTE: ENV's are available in image create and running a container
# Can override these values:
ENV PORT=8080 \
    SPRING_OUTPUT_ANSI_ENABLED="ALWAYS" \
    JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap" \
    CLOUD_CONFIG_SERVER="http://0.0.0.0:8888" \
#    HEALTHCHECK_URI=http://0.0.0.0:8080/health \
    USERNAME="spring" \
    USER_ID="1000"



##---------------------------------------------------------------------------##
# NOTE: Set the WORKDIR. All following commands will be run in this directory.
WORKDIR ${APPLICATION_PATH}

##---------------------------------------------------------------------------##
## Copy all shell scripts:
COPY ./*entrypoint.sh ${APPLICATION_PATH}/

## Copy Binary Files:
ADD ./build/libs/*-exec.jar ${APPLICATION_PATH}/application-exec.jar


##---------------------------------------------------------------------------##
## Option to externalize the OS commands:
COPY ./*container-start.sh ${APPLICATION_PATH}/

RUN chmod -v +x ./container-start.sh && \
    ./container-start.sh && \
    rm ./container-start.sh


##---------------------------------------------------------------------------##
# Now create a new USER and make them the owner of our application files:
RUN adduser -H -D ${USERNAME} -u ${USER_ID} && \
    chown ${USERNAME}:${USERNAME} -R ${APPLICATION_PATH}/

##---------------------------------------------------------------------------##
# NOTE: We should not run the application as root !
#USER spring
USER ${USERNAME}

##---------------------------------------------------------------------------##
# NOTE: RUN is run when building the image NOT when running the container:
# NOTE: CMD is run when building the container NOT when creating the image:
RUN echo "$NOTE_RUN"


##---------------------------------------------------------------------------##
# Note: Which ports to expose to the outside host
# Can be enabled and/or overridden with -e, -p, docker-compose
# -e [HOST:CONTAINER]
# -p [HOST:CONTAINER]
#EXPOSE $PORT


##---------------------------------------------------------------------------##
# NOTE: Entry Points:
# Entry Point Script:
#ENTRYPOINT ./client-entrypoint.sh

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

ENV HEALTHCHECK_URI=localhost:$PORT/health

# Spring Boot Actuator: TODO: Revisit syntax:
HEALTHCHECK --interval=10s --timeout=2s --retries=3 \
    CMD curl -v --silent $HEALTHCHECK_URI 2>&1

#HEALTHCHECK --interval=10s --timeout=3s --retries=3 \
#    CMD curl -f $HEALTHCHECK_URI || exit 1

#HEALTHCHECK --interval=10s --timeout=3s --retries=3 \
#    CMD ["http","$HEALTHCHECK_URI"]


##---------------------------------------------------------------------------##
##---------------------------------------------------------------------------##
