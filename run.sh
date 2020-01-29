#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

SPRING_PROFILES_ACTIVE=heroku

./gradlew composeUp
./gradlew clean bootRun -Pdeveloping
./gradlew composeDown


