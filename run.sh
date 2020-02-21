#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

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

#bootRunHerokuLocals
#bootRunPostgresSQl
bootRunH2
