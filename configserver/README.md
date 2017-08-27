# README #

This readme contains notes on this Config Server

### Distributed configuration with Spring Cloud Config ###
[Udemy Course](https://www.udemy.com/distributed-configuration-with-spring-cloud-config)


#### Spring Cloud Config Server ####

* Quick summary
* Version
* [Pact Publishing](https://bitbucket.org/tutorials/markdowndemo)

This code has been developed in Maven and Gradle and has a sub-project for each chapter


### Testing Existing Configuration ###

#### default Profile ####
Access configuration for microservices-client application for default profile (without BASIC authentication):
[http://localhost:8888/microservices-client/default](http://localhost:8888/microservices-client/default)


Access configuration for microservices-client application for default profile (with BASIC authentication):
[http://user1:changeme@localhost:8888/microservices-client/default](http://user1:changeme@localhost:8888/microservices-client/default)


#### dev Profile ####
Access configuration for microservices-client application for default profile (without BASIC authentication):
[http://localhost:8888/microservices-client/dev](http://localhost:8888/microservices-client/dev)


Access configuration for microservices-client application for default profile (with BASIC authentication):
[http://user1:changeme@localhost:8888/microservices-client/dev](http://user1:changeme@localhost:8888/microservices-client/dev)


curl to verify headers:
curl -I


Access using curl command:
curl http://localhost:8888/microservices-client/default
curl http://user1:changeme@localhost:8888/microservices-client/default




## the end... ##
