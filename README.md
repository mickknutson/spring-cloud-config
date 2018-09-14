# Distributed configuration with Spring Cloud Config

Centralized, distributed external configuration management backed by a GIT repository.

## **Distributed configuration with Spring Cloud Config Class:**

Spring Batch is an open source, lightweight, and comprehensive solution designed to enable the development of robust batch applications that are vital for enterprise operations. Organizations need to process huge volumes of data through a series of transactions in their day-to-day operations. These business operations should be automated to process the information efficiently without human intervention. Batch processing can execute such a series of operations through programs, with a predefined set of data groups as input, process the data, and generate a set of output data groups and update the database.


## **In this course, you'll learn:**

This course will lead intermediate to advanced SpringFramework and Spring Boot developers in creating a centralized Cloud Configuration server for use in cloud environments and MicroService architectures.

The course starts by creating a centralized Spring Cloud Config server. Students will learn how this can be used with or without a cloud oriented system like AWS. This technique can give great flexibility and manageability to any Spring-based deployment.

Students will create Spring Boot projects based mostly on Maven, but will also learn how to create the same projects using Gradle.

Then students will create a local GIT repository and pushing configuration files the students have created.

Then students will create remote Spring Boot clients to pull configuration from the Central config server, detailing out the various configuration that each remote application retrieve.

The students will then cover additional topics such as GIT TAG's and BRANCH's, and how applications can pull from them.

Students will also learn techniques for dynamically changing version of configuration to be pulled from a Spring Cloud Config server, for applications like parallel Microservice deployments.

**Spring Cloud Config Server features:**

-   HTTP, resource-based API for external configuration (name-value pairs, or equivalent YAML content)
-   Encrypt and decrypt property values (symmetric or asymmetric)
-   Embeddable easily in a Spring Boot application using @EnableConfigServer
-   Enable BASIC Authentication for configuration access


**Config Client features (for Spring applications):**

-   Bind to the Config Server and initialize Spring Environment with remote property sources
-   Bind directly to complex Objects using @ConfigurationProperties with remote property source
-   Enable BASIC Authentication for configuration access




### **What are the requirements?**



*   A PC or Mac
*   Internet Access
*   Basic Java knowledge is mandatory
*   Java JDK 8
*   IntelliJ or Eclipse is helpful
*   Basic Knowledge of Spring Boot is helpful


### **What am I going to get from this course?**


*   **NOTE:** There is ZERO XML Config in this course!!!
*   Configure all aspects of batch jobs using **_JavaConfig_**
*   Everything in this course has been unit, integration and functionally tested with JUnit
*   TBD...


### **What is the target audience?**



*   Web Developers
*   Software Developers
*   Programmers
*   Anyone who wants to learn Spring Batch

---
## **Source Code Root**
The root of the labs and solutions is located:
https://github.com/mickknutson/spring_batch_course/tree/master/StudentWork/code

---
## **Available on Udemy.com**
https://www.udemy.com/distributed-configuration-with-spring-cloud-config/



## **Instructor References:**

- **YouTube Channel:** [https://www.youtube.com/c/BASELogic]
- **Udemy.com Instructor Profile:** [https://www.udemy.com/user/mickknutson/]
- **Spring Security 3rd Edition:** [https://packtpub.com/application-development/spring-security-third-edition]
- **Spring Cloud Config Video:** [https://udemy.com/distributed-configuration-with-spring-cloud-config]
- **Java EE6 Cookbook:** [http://packtpub.com/java-ee6-securing-tuning-extending-enterprise-applications-cookbook/book]
- **HTTP Reference Card:** [http://refcardz.dzone.com/refcardz/http-hypertext-transfer-0]
- **VisualVM Reference Card:** [http://refcardz.dzone.com/refcardz/java-profiling-visualvm]


---
 ## Project Status

![https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)

[![Bugs (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=bugs)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=bugs) [![code_smells (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=code_smells)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=code_smells) [![sqale_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=sqale_rating)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=sqale_rating) [![coverage (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=coverage) [![ncloc (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=ncloc)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=ncloc) [![reliability_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=reliability_rating)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=reliability_rating) [![security_rating (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=security_rating)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=security_rating) [![sqale_index (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=sqale_index)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=sqale_index) [![vulnerabilities (%)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=vulnerabilities)](https://sonarcloud.io/api/project_badges/measure?project=mickknutson_spring_batch_course&metric=vulnerabilities)



[![CircleCI](https://circleci.com/gh/mickknutson/spring_batch_course.svg?style=svg)](https://circleci.com/gh/mickknutson/spring_batch_course)

 [![codecov](https://codecov.io/gh/mickknutson/spring_batch_course/branch/master/graph/badge.svg)](https://codecov.io/gh/mickknutson/spring_batch_course)
 [![Coverage Status](https://coveralls.io/repos/github/mickknutson/spring_batch_course/badge.svg?branch=master)](https://coveralls.io/github/mickknutson/spring_batch_course?branch=master)

---



# README #

This contains details for the solutions code, lab code, and git repositories.

### Spring Cloud Config Course ###

* [Udemy](https://udemy.com/user/mickknutson/)

This code has been developed in Maven

### ./configserver ###
This directory contains the Spring Cloud Config Server project.

### ./microservicesclient ###
This directory contains the Spring Cloud Config Client project.

### ./student_files ###
This directory is the git repository for the examples used in the main course.


## Docker

### Compose Up:

    docker-compose up

### Compose down:

    docker-compose down


## the end...
