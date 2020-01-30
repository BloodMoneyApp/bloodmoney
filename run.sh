#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

function bootRunHeroku() {
    ./gradlew composeUp
    ./gradlew clean bootRun --args='--spring.profiles.active=heroku'
    ./gradlew composeDown
}

function bootRunDefault() {
    ./gradlew clean bootRun --args='--spring.profiles.active=default'
}

#bootRunHeroku
bootRunDefault
