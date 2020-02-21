#!/usr/bin/env bash

source setenv.sh


function bootRunHerokuLocal() {
    ./gradlew -i composeUp
    ./gradlew -i clean assemble
    heroku local web
    ./gradlew -i composeDown
}

function bootRunPostgresSQl() {
    ./gradlew -i composeUp
    ./gradlew -i clean bootRun --args='--spring.profiles.active=default'
    ./gradlew -i composeDown
}

function testH2() {
    ./gradlew -i clean build test check bootJar
    #--args='--spring.profiles.active=dev'
}

function bootRunH2() {
    ./gradlew -i clean bootRun --args='--spring.profiles.active=dev'
}

#bootRunHerokuLocal

#bootRunPostgresSQl

testH2

#bootRunH2
