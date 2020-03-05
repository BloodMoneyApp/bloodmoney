#!/usr/bin/env bash

source setenv.sh

function composeUp() {
    ./gradlew -i composeUp
}

function composeDown() {
    ./gradlew -i composeDown
}

function bootRunHerokuLocal() {
    composeUp
    ./gradlew -i clean assemble
    heroku local web
    composeDown
}

function bootRunPostgresSQL() {
    ./gradlew -i clean assemble bootJar
    composeUp
    ./gradlew -i bootRun --args='--spring.profiles.active=default'
    #composeDown
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
    # composeDown
    composeUp
    # bootRunPostgresSQL
    # testH2
    # bootRunH2
}

main
