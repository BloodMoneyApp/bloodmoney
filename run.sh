#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

function bootRunHeroku() {
    ./gradlew -i composeUp
    ./gradlew -i clean bootRun --args='--spring.profiles.active=heroku'
    ./gradlew -i composeDown
}

function bootRunHerokuLocal() {
    ./gradlew -i composeUp
    ./gradlew -i clean assemble bootJar
    heroku local web
    ./gradlew -i composeDown
}

function bootRunDefault() {
    ./gradlew -i clean bootRun --args='--spring.profiles.active=default'
}

#bootRunHerokuLocal
#bootRunHeroku
#bootRunDefault
bootRunHerokuLocal
