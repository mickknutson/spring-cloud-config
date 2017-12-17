# README #

This readme contains notes on this Config Server

### Distributed configuration with Spring Cloud Config
[Udemy Course](https://www.udemy.com/distributed-configuration-with-spring-cloud-config)


### Spring Cloud Config Server
This code has been developed in Maven and Gradle and has a sub-project for each chapter


Docker
------
Create executable: gradle bootRepackage

Create Image: docker



## Testing Existing Configuration

The format for accessing configuration is:

{application}/{profile}/[ {branch/label} ]

default Profile
-

Access configuration for microservices-client application for default profile (without BASIC authentication):
[http://localhost:8080/microservices-client/default](http://localhost:8080/microservices-client/default)
[http://localhost:8080/microservices-client/default/v.3.0](http://localhost:8080/microservices-client/default/v.3.0)
[http://localhost:8080/microservices-client/default/HOTFIX](http://localhost:8080/microservices-client/default/HOTFIX)

http localhost:8080/microservices-client/default
http localhost:8080/microservices-client/default/v.3.0
http localhost:8080/microservices-client/default/HOTFIX

http localhost:8080/microservices-client/dev/HOTFIX


Access configuration for microservices-client application for default profile (with BASIC authentication):
[http://user2:changeme@localhost:8080/microservices-client/default](http://user2:changeme@localhost:8080/microservices-client/default)
[http://user2:changeme@localhost:8080/microservices-client/default/v.3.0](http://user2:changeme@localhost:8080/microservices-client/default/v.3.0)
[http://user2:changeme@localhost:8080/microservices-client/default/HOTFIX](http://user2:changeme@localhost:8080/microservices-client/default/HOTFIX)

http -a user2:changeme localhost:8080



Verification
---

#### curl to verify headers:

curl -I


Access using curl command:
curl http://localhost:8080/microservices-client/default
curl http://user2:changeme@localhost:8080/microservices-client/default

#### httpie

http -a user2:changeme localhost:8080/microservices-client/default

http localhost:8080/microservices-client/default

JSON Formatter
---
[https://jsonformatter-online.com/](https://jsonformatter-online.com)

## the end... ##

ref: https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html