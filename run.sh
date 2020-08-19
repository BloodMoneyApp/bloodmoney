#!/usr/bin/env bash

source src/main/bash/setenv.sh

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
    ./mvnw -e -DskipTests=true clean package spring-boot:run
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
    ./mvnw -e -DskipTests=true -B -V install -Dmaven.javadoc.skip=true
    ./mvnw -e -DskipTests=true -B -V dependency:purge-local-repository
    ./mvnw -e -DskipTests=true -B -V clean
    ./mvnw -e -DskipTests=true -B -V dependency:resolve dependency:resolve-plugins dependency:sources
    ./mvnw -e -DskipTests=true -B -V dependency:tree
    #./mvnw docker-compose:up
    #docker ps
    ./mvnw -e -DskipTests=true -B -V clean package
    ./mvnw -e -DskipTests=true -B -V site
    #./mvnw docker-compose:down
    #docker ps
}

function releaseMe(){
    ./mvnw -e -DskipTests=true -B -V dependency:purge-local-repository
    ./mvnw -e -DskipTests=true -B -V clean
    ./mvnw -e -DskipTests=true -B -V release:clean
    ./mvnw -e -DskipTests=true -B -V release:prepare
    ./mvnw -e -DskipTests=true -B -V release:perform
}

function main() {
    ## runHerokuLocal
    ## composeDown
    ## composeUp
    ## run
    ## testApp
    ##testAppDev
    # firstSetup
    setupTravis
    # runDev
    # releaseMe
}

main
