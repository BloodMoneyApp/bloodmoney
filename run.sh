#!/usr/bin/env bash

source setenv.sh


function bootRunHerokuLocal() {
    ./gradlew -i composeUp
    ./gradlew -i clean assemble
    heroku local web
    ./gradlew -i composeDown
}

function bootRunPostgresSQL() {
    ./gradlew -i composeUp
    ./gradlew -i clean bootRun --args='--spring.profiles.active=default'
    ./gradlew -i composeDown
}

function testH2() {
    ./gradlew -i clean build test check bootJar
    ./gradlew -i bootRun --args='--spring.profiles.active=dev'
}

function bootRunH2() {
    ./gradlew -i clean bootRun --args='--spring.profiles.active=dev'
}

function main() {
    # bootRunHerokuLocal
    bootRunPostgresSQL
    # testH2
    # bootRunH2
}

main
