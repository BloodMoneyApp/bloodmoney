#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

#SPRING_PROFILES_ACTIVE=heroku

function bootRunHeroku() {
    ./gradlew composeUp
    ./gradlew clean bootRunHeroku
    ./gradlew composeDown
}

function bootRunDefault() {
    ./gradlew clean bootRunDefault
}

#bootRunHeroku
bootRunDefault
