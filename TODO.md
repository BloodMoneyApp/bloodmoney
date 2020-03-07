#TODO:

## IntegrationTests and Unit-Tests
* #30 use Lombok in Test Classes
* #31 use Logging in Test Classes
* #32 Test a SpringMVC Controller
* #33 Test a Rest Controller
* #34 Test a SpringMVC Controller with Spring Security
* #35 Test a Rest Controller with Spring Security

* #36 read, apply and evaluate [Spring Security for Spring Boot Integration Tests](https://www.baeldung.com/spring-security-integration-tests)
* #37 read, apply and evaluate [Testing Spring Boot Spring Security](https://www.codeflow.site/de/article/spring-security-integration-tests)
* #38 read [spring-boot-setup-security-for-testing](https://stackoverflow.com/questions/23335200/spring-boot-setup-security-for-testing)

## DatabaseSchemaEvolution
* #39 add UUID Generation to Contructor and factories 
* #40 add UUID Generation from Contructor and factories to Framework Automatism
* #41 make Flyway and H2 Versions compatible
    * Flyway upgrade or H2 Downgrade
    * Flyway upgrade Version from to 
    * H2 Downgrade Version from 1.4.200 to 1.4.199.
* #42 apply and evaluate Flyway for DatabaseSchemaEvolution: https://flywaydb.org/
* #43 apply and evaluate Liquibase for DatabaseSchemaEvolution: https://www.liquibase.org/
* #44 read, apply and evaluate [Use Liquibase to Safely Evolve Your Database Schema](https://www.baeldung.com/liquibase-refactor-schema-of-java-app)
* #45 read [Liquibase – das Tool für agiles Database Deployment 20.10.2014](https://blog.orbit.de/2014/10/20/liquibase-das-tool-fuer-agiles-database-deployment/)
* #46 read [Getting Started with Liquibase](https://www.liquibase.org/get_started/index.html)
* 

## spring.config.location
* TODO

## JAX-RS (REST)
* Add a JAX-RS Resource

* #29 read, apply and evaluate [REST API with Jersey and Spring](https://www.baeldung.com/jersey-rest-api-with-spring)
* #26 read [Spring Boot JAX-RS Example March 29th, 2019](https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-jax-rs-example/)
* #25 read [JAX-RS is just an API!](https://www.baeldung.com/jax-rs-spec-and-implementations)
* #27 read [AX-RS/Jersey and Spring REST](https://learnjava.co.in/jax-rs-vs-spring-rest/)
* #28 read [Let's Compare: JAX-RS vs Spring for REST Endpoints](https://developer.okta.com/blog/2017/08/09/jax-rs-vs-spring-rest-endpoints)
* read [7 Reasons I Do Not Use JAX-RS in Spring Boot Web Applications](https://dzone.com/articles/7-reasons-i-do-not-use-jax-rs-in-spring-boot-web-a)
* read [Protecting JAX-RS Resources with RBAC and Apache Shiro](https://stormpath.com/blog/protecting-jax-rs-resources-rbac-apache-shiro)
* doc [Jersey](https://eclipse-ee4j.github.io/jersey/)
* doc [Java API for RESTful Web Services, kurz JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services)
* doc [JavaEE 6 Tutorial: Building RESTful Web Services with JAX-RS](https://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)



## JAX-WS (SOAP)
* Add a JAX-WS Endpoint
* [Spring.io Refence-Doc: spring-ws supports_ws_security](https://docs.spring.io/spring-ws/docs/3.0.8.RELEASE/reference/#_supports_ws_security)
* [Spring.io Guide: producing-web-service](https://spring.io/guides/gs/producing-web-service/)
* build.gradle

```

configurations {

    jaxb
}

task genJaxb {
    ext.sourcesDir = "${buildDir}/generated-sources/jaxb"
    ext.classesDir = "${buildDir}/classes/jaxb"
    ext.schema = "src/main/resources/countries.xsd"

    outputs.dir classesDir

    doLast() {
        project.ant {
            taskdef name: "xjc", classname: "com.sun.tools.xjc.XJCTask",
                classpath: configurations.jaxb.asPath
            mkdir(dir: sourcesDir)
            mkdir(dir: classesDir)

            xjc(destdir: sourcesDir, schema: schema) {
                arg(value: "-wsdl")
                produces(dir: sourcesDir, includes: "**/*.java")
            }

            javac(destdir: classesDir, source: 1.6, target: 1.6, debug: true,
                debugLevel: "lines,vars,source", includeantruntime: false,
                classpath: configurations.jaxb.asPath) {
                src(path: sourcesDir)
                include(name: "**/*.java")
                include(name: "*.java")
            }

            copy(todir: classesDir) {
                fileset(dir: sourcesDir, erroronmissingdir: false) {
                    exclude(name: "**/*.java")
                }
            }
        }
    }
}
dependencies {

    jaxb "org.glassfish.jaxb:jaxb-xjc:2.2.11"
    compile(files(genJaxb.classesDir).builtBy(genJaxb))
}

````
