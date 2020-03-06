#!/usr/bin/env bash

source setenv.sh

function composeUp() {
    ./gradlew -i composeUp
}

function composeDown() {
    ./gradlew -i composeDown
}

function bootRunHerokuLocal() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    composeUp
    ./gradlew -i clean assemble
    heroku local web
    composeDown
}

function bootRunPostgresSQL() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    composeUp
    ./gradlew -i clean assemble bootJar bootRun
    composeDown
}

function bootRunH2() {
     export JAVA_OPTS=$JAVA_OPTS_RUN_DEV
    ./gradlew -i clean assemble bootJar bootRun
}

function testH2() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEV
    ./gradlew -i clean assemble bootJar build test check bootRun
}

function main() {
    # bootRunHerokuLocal
    # composeDown
    # composeUp
    # bootRunPostgresSQL
    # testH2
    # bootRunH2
}

main
