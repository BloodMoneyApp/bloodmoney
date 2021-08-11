#!/usr/bin/env bash

source src/main/bash/setenv.sh

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


