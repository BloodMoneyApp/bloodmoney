# Howto Reminder

## 3rd Party Software 
### Howto
* [Spring dependency-management-plugin](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/)
* [Example on github: heroku and Gradle](https://github.com/heroku/gradle-getting-started/blob/master/build.gradle)
* [Howto: Using lombok](https://projectlombok.org/setup/overview)
* [Howto: Gradle Docker Compose](https://bmuschko.com/blog/gradle-docker-compose/)

### Documentation
* http://projects.spring.io/spring-boot/

### Github Repos
* [gradle-docker-compose-plugin](https://github.com/avast/gradle-docker-compose-plugin)

### Gradle Plugins
* https://plugins.gradle.org/plugin/org.springframework.boot
* https://plugins.gradle.org/plugin/org.springframework.cloud.contract
* https://plugins.gradle.org/plugin/io.spring.dependency-management
* https://plugins.gradle.org/plugin/org.asciidoctor.jvm.convert
* https://plugins.gradle.org/plugin/com.avast.gradle.docker-compose


## Database and JPA
### DB Datatypes
* [H2 Datatypes](http://www.h2database.com/html/datatypes.html)
* [PostgreSQL Datatypes](https://www.postgresql.org/docs/11/datatype.html)
* LocalDateTime and TimeZone: TODO

### UUID and Optimistic Locking
* UUID: TODO

### Database Schema Evolution with Spring Boot JPA
* TODO
* org.flywaydb:flyway
* org.liquibase:liquibase
 

## Frontend with webjars

### updating webjar Versions:
* change Version in build.gradle "ext" (Line 40)
* change Version in src/main/resources/templates/layout/page.html Section head.tw-head and div.tw-footer (Lines 16, 98) 
