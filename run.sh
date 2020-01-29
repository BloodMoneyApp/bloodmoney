#!/usr/bin/env bash

source setenv.sh

#./gradlew assemble

./gradlew composeUp
./gradlew clean bootRun -Pdeveloping
./gradlew composeDown


