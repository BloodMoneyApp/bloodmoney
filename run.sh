#!/usr/bin/env bash

source etc/setenv.sh

function composeUp() {
    ./gradlew -i composeUp
}

function composeDown() {
    ./gradlew -i composeDown
}

function runHerokuLocal() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    composeUp
    ./gradlew -i clean assemble
    heroku local web
    composeDown
}

function testApp() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./gradlew -i clean assemble bootJar
    composeUp
    ./gradlew -i build test check
    composeDown
}

function run() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    composeUp
    ./gradlew -i clean assemble bootJar bootRun
    composeDown
}

function main() {
    # runHerokuLocal
    # composeDown
    # composeUp
    run
    # testApp
}

main
