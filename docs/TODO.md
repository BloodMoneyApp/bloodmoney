#TODO:

## github
* [Issues](https://github.com/BloodMoneyApp/bloodmoney/issues)
* [Projects](https://github.com/BloodMoneyApp/bloodmoney/projects)
* [Milestones](https://github.com/BloodMoneyApp/bloodmoney/milestones)
* [Releases](https://github.com/BloodMoneyApp/bloodmoney/releases)

## Development
* [Changelog](docs/RELEASES.md)
* [Todo](docs/TODO.md)
* [Howto](docs/HOWTO.md)

## Todos
* spring.config.location
* read [7 Reasons I Do Not Use JAX-RS in Spring Boot Web Applications](https://dzone.com/articles/7-reasons-i-do-not-use-jax-rs-in-spring-boot-web-a)
* read [Protecting JAX-RS Resources with RBAC and Apache Shiro](https://stormpath.com/blog/protecting-jax-rs-resources-rbac-apache-shiro)
* doc [Jersey](https://eclipse-ee4j.github.io/jersey/)
* doc [Java API for RESTful Web Services, kurz JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services)
* doc [JavaEE 6 Tutorial: Building RESTful Web Services with JAX-RS](https://docs.oracle.com/javaee/6/tutorial/doc/giepu.html)
* doc [Spring.io Refence-Doc: spring-ws supports_ws_security](https://docs.spring.io/spring-ws/docs/3.0.8.RELEASE/reference/#_supports_ws_security)
* doc [Spring.io Guide: producing-web-service](https://spring.io/guides/gs/producing-web-service/)

next commit:

fixed #49 remove cookieconsent2/3.1.0/cookieconsent.min.js



## JAX-WS (SOAP)
* Add a JAX-WS - SOAPEndpoint
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
