# bloodmoney

[![Java CI with Maven](https://github.com/BloodMoneyApp/bloodmoney/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/BloodMoneyApp/bloodmoney/actions)
[![OSSAR](https://github.com/BloodMoneyApp/bloodmoney/workflows/OSSAR/badge.svg)](https://github.com/BloodMoneyApp/bloodmoney/actions)
[![Codacy Security Scan](https://github.com/BloodMoneyApp/bloodmoney/workflows/Codacy%20Security%20Scan/badge.svg)](https://github.com/BloodMoneyApp/bloodmoney/actions)
[![Build Status](https://travis-ci.com/BloodMoneyApp/bloodmoney.svg?branch=master)](https://travis-ci.com/BloodMoneyApp/bloodmoney)

Webapp for storing Blood Pressure Measurements for one Person.

## github
* [Issues](https://github.com/BloodMoneyApp/bloodmoney/issues)
* [Projects](https://github.com/BloodMoneyApp/bloodmoney/projects)
* [Milestones](https://github.com/BloodMoneyApp/bloodmoney/milestones)
* [Releases](https://github.com/BloodMoneyApp/bloodmoney/releases)

## Development
* [Changelog](src/site/markdown/CHANGELOG.md)
* [Todo](etc/TODO.md)
* [Howto](src/site/markdown/HOWTO.md)

## Trivia
The Name Bloodmoney was inspired by the Song "bloodmoney" on the 12" Vinyl EP 
[The Sisters Of Mercy: No Time To Cry (UK & Europe, Feb 1985)](https://www.discogs.com/The-Sisters-Of-Mercy-No-Time-To-Cry/release/6717124)

## Feature Request Backlog
* Feature #142 CRUD for Measurements
* Feature #141 Very Simple Login
* Feature #140 Frontend: Thmyleaf, Bootstrap
* Feature #138 Deployment om Heroku
* Feature #136 Better Login and User Selfservice
* Feature #135 Soap Webservice with Login and CRUD for Measurements
* Feature #134 Test Client for Soap Webservice
* Feature #133 REST Webservice with Login and CRUD for Measurements
* Feature #132 Test Client for REST Webservice
* Feature #139 Webapp shall send Emails

## Todos
* spring.config.location
* read [7 Reasons I Do Not Use JAX-RS in Spring Boot Web Applications](https://dzone.com/articles/7-reasons-i-do-not-use-jax-rs-in-spring-boot-web-a)
* read [Protecting JAX-RS Resources with RBAC and Apache Shiro](https://stormpath.com/blog/protecting-jax-rs-resources-rbac-apache-shiro)
* doc [Jersey](https://eclipse-ee4j.github.io/jersey/)
* doc [Java API for RESTful Web Services, kurz JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services)
* doc [JavaEE 6 Tutorial: Building RESTful Web Services with JAX-RS](https://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)
* doc [Spring.io Refence-Doc: spring-ws supports_ws_security](https://docs.spring.io/spring-ws/docs/3.0.8.RELEASE/reference/#_supports_ws_security)
* doc [Spring.io Guide: producing-web-service](https://spring.io/guides/gs/producing-web-service/)

## next commit:
* fixed # 36 apply Spring Security for Spring Boot Integration Tests

## 1.8.3
* Fixed #53
* Fixed #55
* Fixed #56
* Fixed #57 
* Fixed #59
* Fixed #60

## 1.8.4
* Fixed #24

## 1.8.5
* fixed #67 Update to Spring-Boot 2.3.3 
* fixed #68 Update to Spring Data Bom Neumann-SR3 for Spring-Boot 2.3.3
* fixed #69 Update to Spring Framework for Spring-Boot 2.3.3
* fixed #70 Update to Spring Security for Spring-Boot 2.3.3

## 1.8.6
* merge branches
* fixed #78 Release 1.8.6

## 1.8.7
* release plugin

## 1.8.8
* fixed #79 merge branches
* fixed #80 release plugin 
* fixed #75 SLF4J: Class path contains multiple SLF4J bindings
* fixed #50 add bootwatch for bootstrap

## 1.8.9
* fixed #81 mvnw docker-compose:up 
* fixed #84 update webjars

## 1.8.10
* Merge #86
 
## 1.8.11
* fixed #87 update spring-boot to 2.3.4
* fixed #88 update spring-data-releasetrain to Neumann-SR4
* fixed #89 update spring-session-bom to Dragonfruit-SR1
* fixed #90 update spring-security-bom
* fixed #91 update spring-framework-bom
* fixed #92 update spring-integration-bom
* fixed #93 update webjars
* fixed #94 add github actions for CI 

## 1.8.12
* fixed #113 check dependency: spring-boot-admin-starter-client
* fixed #114 check dependency: spring-boot-admin-starter-server
* fixed #95 check dependency: thymeleaf-testing
* fixed #96 check dependency: thymeleaf-spring-data-dialect
* fixed #119 check dependency: thymeleaf
* fixed #120 check dependency: thymeleaf-spring5
* fixed #121 check dependency: thymeleaf-extras-java8time
* fixed #122 check dependency: thymeleaf-extras-springsecurity5
* fixed #123 check dependency: thymeleaf-extras-data-attribute
* fixed #124 check dependency: thymeleaf-spring-data-dialect
* fixed #107 check dependency: spring-boot-starter-mustache
* fixed #101 check dependency: spring-boot-devtools
* fixed #102 check dependency: spring-boot-configuration-processor
* fixed #103 check dependency: spring-boot-properties-migrator
* fixed #137 Webapp shall send Emails
* fixed #109 check dependency: spring-boot-starter-mail
* fixed #48 Add a REST Controller Resource for org.woehlke.bloodmoney.measurements.BloodPressureMeasurement
* fixed #106 check dependency: spring-boot-starter-jersey
* fixed #111 check dependency: spring-boot-starter-json
* fixed #128 check dependency: jaxb-api
* fixed #125 check dependency: opencsv
* fixed #104 check dependency: spring-boot-starter-data-rest
* fixed #105 check dependency: spring-boot-starter-hateoas
* fixed #117 check dependency: spring-data-rest-hal-explore
* fixed #118 check dependency: spring-data-rest-hal-explore 
* fixed #130 check dependency: spring-restdocs-mockmvc
* fixed #108 check dependency: spring-boot-starter-web-services
* fixed #110 check dependency: spring-boot-starter-cache
* fixed #126 check dependency: jsoup
* fixed #115 check dependency: spring-session-core
* fixed #116 check dependency: spring-session-jdbc
* fixed #127 check dependency: hibernate-core
* fixed #129 check dependency: postgresql
* fixed #112 check dependency: spring-boot-starter-test
* issue #97 check dependency: jsoup
* issue #99 check dependency: asm
* issue #100 check dependency: attoparser
* issue #98 check dependency: opencsv

## 1.8.13
* issue #82 implement void updateLastLoginTimestamp(UserAccountBean user);
