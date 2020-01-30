#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

function bootRunHeroku() {
    #export SPRING_PROFILES_ACTIVE=heroku
    ./gradlew composeUp
    ./gradlew clean bootRunHeroku
    ./gradlew composeDown
}

function bootRunDefault() {
    #export SPRING_PROFILES_ACTIVE=default
    ./gradlew clean bootRun
}

#bootRunHeroku
bootRunDefault
