#!/usr/bin/env bash

source setenv.sh


function bootRunHerokuLocal() {
    ./gradlew composeUp
    ./gradlew clean assemble
    heroku local web
    ./gradlew composeDown
}

function bootRunPostgresSQl() {
    ./gradlew composeUp
    ./gradlew clean bootRun --args='--spring.profiles.active=default'
    ./gradlew composeDown
}

function testH2() {
    ./gradlew clean build bootRun --args='--spring.profiles.active=dev'
}

function bootRunH2() {
    ./gradlew clean bootRun --args='--spring.profiles.active=dev'
}

#bootRunHerokuLocal

#bootRunPostgresSQl

#testH2

bootRunH2
