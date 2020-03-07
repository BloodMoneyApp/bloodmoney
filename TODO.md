#TODO:

## IntegrationTests
* use Lombok in Test Classes
* Logging
* Test a SpringMVC Controller
* Test a Rest Controller
* Use Spring Security for Testing Controllers [Spring Security for Spring Boot Integration Tests](https://www.baeldung.com/spring-security-integration-tests)


## DatabaseSchemaEvolution
* add new with UUID
* Flyway: https://flywaydb.org/
* Flyway upgrade or H2 Downgrade
* Flyway upgrade Version from to 
* H2 Downgrade Version from 1.4.200 to 1.4.199.
2020-03-06 16:50:27.034  WARN 2897 --- [    Test worker] o.f.c.internal.database.base.Database    : Flyway upgrade recommended: H2 1.4.200 is newer than this version of Flyway and support has not been tested. The latest supported version of H2 is 1.4.199.


* TODO

## spring.config.location
* TODO

## REST JAX-RS
* Add a JAX-RS Resource
* TODO

## SOAP JAX-WS
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
