# README #

This readme contains notes on this Config Server

### Distributed configuration with Spring Cloud Config
[Udemy Course](https://www.udemy.com/distributed-configuration-with-spring-cloud-config)


### Spring Cloud Config Server
This code has been developed in Maven and Gradle and has a sub-project for each chapter


## Testing Existing Configuration

The format for accessing configuration is:

{application}/{profile}/[ {branch/label} ]

default Profile
-

Access configuration for microservices-client application for default profile (without BASIC authentication):
[http://localhost:8888/microservices-client/default](http://localhost:8888/microservices-client/default)
[http://localhost:8888/microservices-client/default/v.3.0](http://localhost:8888/microservices-client/default/v.3.0)
[http://localhost:8888/microservices-client/default/HOTFIX](http://localhost:8888/microservices-client/default/HOTFIX)

http localhost:8888/microservices-client/default
http localhost:8888/microservices-client/default/v.3.0
http localhost:8888/microservices-client/default/HOTFIX


Access configuration for microservices-client application for default profile (with BASIC authentication):
[http://user1:changeme@localhost:8888/microservices-client/default](http://user1:changeme@localhost:8888/microservices-client/default)
[http://user1:changeme@localhost:8888/microservices-client/default/v.3.0](http://user1:changeme@localhost:8888/microservices-client/default/v.3.0)
[http://user1:changeme@localhost:8888/microservices-client/default/HOTFIX](http://user1:changeme@localhost:8888/microservices-client/default/HOTFIX)

http -a user1:changeme localhost:8888/microservices-client/default
http -a user1:changeme localhost:8888/microservices-client/default/v.3.0
http -a user1:changeme localhost:8888/microservices-client/default/HOTFIX


dev Profile
-
Access configuration for microservices-client application for default profile (without BASIC authentication):
[http://localhost:8888/microservices-client/dev](http://localhost:8888/microservices-client/dev)
[http://localhost:8888/microservices-client/dev/v.3.0](http://localhost:8888/microservices-client/dev/v.3.0)
[http://localhost:8888/microservices-client/dev/HOTFIX](http://localhost:8888/microservices-client/dev/HOTFIX)

http localhost:8888/microservices-client/dev
http localhost:8888/microservices-client/dev/v.3.0
http localhost:8888/microservices-client/dev/HOTFIX


Access configuration for microservices-client application for default profile (with BASIC authentication):
[http://user1:changeme@localhost:8888/microservices-client/dev](http://user1:changeme@localhost:8888/microservices-client/dev)
[http://user1:changemelocalhost:8888/microservices-client/dev/v.3.0](http://user1:changemelocalhost:8888/microservices-client/dev/v.3.0)
[http://user1:changemelocalhost:8888/microservices-client/dev/HOTFIX](http://user1:changemelocalhost:8888/microservices-client/dev/HOTFIX)

http -a user1:changeme localhost:8888/microservices-client/dev
http -a user1:changeme localhost:8888/microservices-client/dev/v.3.0
http -a user1:changeme localhost:8888/microservices-client/dev/HOTFIX

Verification
-

#### curl to verify headers:

curl -I


Access using curl command:
curl http://localhost:8888/microservices-client/default
curl http://user1:changeme@localhost:8888/microservices-client/default

#### httpie

http -a user1:changeme localhost:8888/microservices-client/default

http localhost:8888/microservices-client/default

JSON Formatter
-
[https://jsonformatter-online.com/](https://jsonformatter-online.com)

## the end... ##

ref: https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html