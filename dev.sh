#!/usr/bin/env bash

#source src/main/bash/setenv.sh
source ~/.bash_aliases_bloodmoney

./mvnw clean install spring-boot:repackage

sudo cp -f target/bloodmoney.war /var/lib/tomcat9/webapps/
