#!/usr/bin/env bash

source src/main/bash/setenv.sh

function composeUp() {
    ./mvnw docker-compose:up
}

function composeDown() {
    ./mvnw docker-compose:down
}

function firstSetup() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    export TW_SKIP_TESTS='-DskipTests=true'
    showSettings
    ./mvnw dependency:purge-local-repository
    ./mvnw -e $TW_SKIP_TESTS clean dependency:resolve dependency:resolve-plugins dependency:sources dependency:tree
    ./mvnw -e $TW_SKIP_TESTS clean package spring-boot:repackage site site:deploy
}

function setupTravis() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    export TW_SKIP_TESTS='-DskipTests=true'
    showSettings
    ./mvnw -e $TW_SKIP_TESTS -B -V install -Dmaven.javadoc.skip=true && \
    ./mvnw -e $TW_SKIP_TESTS -B -V dependency:purge-local-repository clean && \
    ./mvnw -e $TW_SKIP_TESTS -B -V dependency:resolve dependency:resolve-plugins dependency:sources dependency:tree && \
    ./mvnw -e $TW_SKIP_TESTS -B -V clean package spring-boot:repackage && \
    ./mvnw -e $TW_SKIP_TESTS -B -V site site:deploy
}

function main() {
    # firstSetup
    setupTravis
}

main
