#!/usr/bin/env bash

source setenv.sh

function bootRunPostgresSQl() {
    ./gradlew composeUp
    ./gradlew clean bootRun --args='--spring.profiles.active=heroku'
    ./gradlew composeDown
}

function bootRunHerokuLocal() {
    ./gradlew composeUp
    ./gradlew clean assemble
    heroku local web
    ./gradlew composeDown
}

function bootRunH2() {
    ./gradlew clean bootRun --args='--spring.profiles.active=default'
}

#bootRunHerokuLocal

#bootRunPostgresSQl

bootRunH2
