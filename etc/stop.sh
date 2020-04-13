#!/usr/bin/env bash

source setenv.sh

export BLOODMONEY_DEV_TESTING=false
export SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE_DEFAULT
export JAVA_OPTS=$JAVA_OPTS_DEFAULT
../gradlew -i composeDown
../gradlew -i clean
