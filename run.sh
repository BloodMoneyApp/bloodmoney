#!/usr/bin/env bash

source etc/bash/setenv.sh


function herokuRun() {
    ./mvnw -Pjar
    JAVA_TOOL_OPTIONS=' -XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8'
    JAVA_OPTIONS=JAVA_OPTIONS:$JAVA_TOOL_OPTIONS
    java -jar target/bloodmoney.jar org.woehlke.bloodmoney.BloodMoneyApplication -Dspring.profiles.active=default
}

function run() {
    ./mvnw
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

herokuRun


