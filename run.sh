#!/usr/bin/env bash

source etc/bash/setenv.sh

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
    ./mvnw -e -DskipTests=true dependency:purge-local-repository clean package spring-boot:run
}

function firstSetup() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./mvnw dependency:purge-local-repository
    ./mvnw -e $TW_SKIP_TESTS clean install
    ./mvnw -e $TW_SKIP_TESTS dependency:tree dependency:resolve dependency:resolve-plugins dependency:sources
    ./mvnw -e $TW_SKIP_TESTS site site:deploy
    ./mvnw -e $TW_SKIP_TESTS clean install spring-boot:repackage
}

function setupTravis() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    showSettings
    ./mvnw -e -DskipTests=true -B -V install -Dmaven.javadoc.skip=true
    ./mvnw -e -DskipTests=true -B -V dependency:purge-local-repository
    ./mvnw -e -DskipTests=true -B -V clean
    ./mvnw -e -DskipTests=true -B -V dependency:resolve dependency:resolve-plugins dependency:sources dependency:tree
    #./mvnw docker-compose:up
    #docker ps
    ./mvnw -e -DskipTests=true -B -V clean package
    ./mvnw -e -DskipTests=true -B -V site
    #./mvnw docker-compose:down
    #docker ps
}

function main() {
    ## runHerokuLocal
    ## composeDown
    ## composeUp
    ## run
    ## testApp
    ##testAppDev
    firstSetup
    # setupTravis
    runDev
}

main


