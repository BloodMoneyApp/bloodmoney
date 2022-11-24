#!/usr/bin/env bash

source src/main/bash/setenv.sh

./mvnw site site:deploy
