#TODO:

## DatabaseSchemaEvolution
* TODO

## spring.config.location
* TODO

## REST JAX-RS
* TODO

## SOAP JAX-WS
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