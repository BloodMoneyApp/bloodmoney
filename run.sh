#!/usr/bin/env bash

source etc/setenv.sh

function composeUp() {
    ./mvnw docker-compose:up
}

function composeDown() {
    ./mvnw docker-compose:down
}

function runHerokuLocal() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    composeUp
    ./mvnw clean package
    heroku local web
    composeDown
}

function testApp() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./mvnw clean package bootJar
    composeUp
    ./mvnw install test check
    composeDown
}

function run() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    composeUp
    ./mvnw -e clean spring-boot:run
    composeDown
}

function testAppDev() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./mvnw clean package bootJar
    ./mvnw install test check
}

function runDev() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./mvnw -e -DskipTests=true clean dependency:tree package spring-boot:run
}

function firstSetup() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./mvnw dependency:purge-local-repository
    ./mvnw -e -DskipTests=true clean dependency:resolve dependency:resolve-plugins dependency:sources dependency:tree
    ./mvnw -e -DskipTests=true clean package site
}
function setupTravis() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    #./mvnw clean
    #./mvnw dependency:purge-local-repository
    #./mvnw install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
    ./mvnw dependency:purge-local-repository
    ./mvnw clean
    ./mvnw dependency:resolve dependency:resolve-plugins dependency:sources -DskipTests=true -B -V
    ./mvnw dependency:tree
    ./mvnw docker-compose:up
    docker ps
    #./mvnw clean package site -DskipTests=true -B -V
    ./mvnw docker-compose:down
    docker ps
}

function main() {
    ## runHerokuLocal
    ## composeDown
    ## composeUp
    ## run
    ## testApp
    #runDev
    ##testAppDev
    firstSetup
    # setupTravis
}

main
