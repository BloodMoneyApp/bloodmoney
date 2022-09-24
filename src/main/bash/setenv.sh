#!/usr/bin/env bash

#echo "SETUP Environment"
#echo ""
#echo "PostgreSQL Database from Docker: bloodmoney_dbmaster"
export BLOODMONEY_DS_DB='bloodmoney'
export BLOODMONEY_DS_USR='bloodmoney'
export BLOODMONEY_DS_PWD='bloodmoneypwd'
export BLOODMONEY_DS_HOST='localhost'
export BLOODMONEY_DS_PORT=5432
#echo ""
#echo "PostgreSQL Database from Docker: bloodmoney_dbref"
export BLOODMONEY_DSREF_DB='bloodmoneyref'
export BLOODMONEY_DSREF_USR='bloodmoneyref'
export BLOODMONEY_DSREF_PWD='bloodmoneyrefpwd'
export BLOODMONEY_DSREF_HOST='localhost'
export BLOODMONEY_DSREF_PORT=5464
#echo ""
#echo "Persistence Settings"
#export BLOODMONEY_HIBERNATE_DDL_AUTO='create-drop'
export BLOODMONEY_HIBERNATE_DDL_AUTO='update'
#echo ""
#echo "Credentials for Testing"
export BLOODMONEY_USER_EMAIL='thomas.woehlke@gmail.com'
export BLOODMONEY_USER_FULLNAME='Thomas Woehlke'
export BLOODMONEY_USER_ITERATIONS=4096
export BLOODMONEY_USER_HASH_WIDTH=256
export BLOODMONEY_USER_SECRET=25D8484AB208F08C59FFAFE57DB9FF87
export BLOODMONEY_USER_PASSWORD=gdEB4vO/E8ArK1X1Cj2bnKGDPiJPUusEJfxxzpgUsf4G2fIrz+bHoQ==
#echo ""
#echo "Runtime and Application Service Settings"
export PORT=8080
export BLOODMONEY_DEV_TESTING=false
export SPRING_PROFILES_ACTIVE=dev
export JAVA_OPTS_GRADLE='-Xmx1g -XX:MaxMetaspaceSize=256m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8'
export JAVA_OPTS_RUNTIME='-Xmx300m -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -XX:CICompilerCount=2'
export JAVA_OPTS_RUN_DEV="-Dspring.profiles.active=dev -Dspring.jmx.enabled=true $JAVA_OPTS_RUNTIME"
export JAVA_OPTS_RUN_DEFAULT="-Dspring.profiles.active=default $JAVA_OPTS_RUNTIME"
#echo ""
#echo "SETUP Environment DONE"
#echo ""

function showSettings_bloodmoney_dbmaster() {
    echo "PostgreSQL Database from Docker: bloodmoney_dbmaster"
    echo "BLOODMONEY_DS_DB=$BLOODMONEY_DS_DB"
    echo "BLOODMONEY_DS_USR=$BLOODMONEY_DS_USR"
    echo "BLOODMONEY_DS_PWD=$BLOODMONEY_DS_PWD"
    echo "BLOODMONEY_DS_HOST=$BLOODMONEY_DS_HOST"
    echo "BLOODMONEY_DS_PORT=$BLOODMONEY_DS_PORT"
}

function showSettings_bloodmoney_dbref() {
    echo "PostgreSQL Database from Docker: bloodmoney_dbref"
    echo "BLOODMONEY_DSREF_DB=$BLOODMONEY_DSREF_DB"
    echo "BLOODMONEY_DSREF_USR=$BLOODMONEY_DSREF_USR"
    echo "BLOODMONEY_DSREF_PWD=$BLOODMONEY_DSREF_PWD"
    echo "BLOODMONEY_DSREF_HOST=$BLOODMONEY_DSREF_HOST"
    echo "BLOODMONEY_DSREF_PORT=$BLOODMONEY_DSREF_PORT"
}

function showSettingsPersistence(){
    echo "Persistence Settings"
    echo "BLOODMONEY_HIBERNATE_DDL_AUTO=$BLOODMONEY_HIBERNATE_DDL_AUTO"
}

function showSettingsCredentialsForTesting() {
    echo "Credentials for Testing"
    echo "BLOODMONEY_USER_EMAIL=$BLOODMONEY_USER_EMAIL"
    echo "BLOODMONEY_USER_FULLNAME=$BLOODMONEY_USER_FULLNAME"
    echo "BLOODMONEY_USER_INTERATIONS=$BLOODMONEY_USER_INTERATIONS"
    echo "BLOODMONEY_USER_HASH_WIDTH=$BLOODMONEY_USER_HASH_WIDTH"
    echo "BLOODMONEY_USER_SECRET=$BLOODMONEY_USER_SECRET"
    echo "BLOODMONEY_USER_PASSWORD=$BLOODMONEY_USER_PASSWORD"
}

function showSettingsRuntimesettings() {
    echo "Runtime and Application Service Settings"
    echo "PORT=$PORT"
    echo "BLOODMONEY_DEV_TESTING=$BLOODMONEY_DEV_TESTING"
    echo "SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE"
}

function showSettingsJavaOptsVariants() {
    echo "JAVA_OPTS_GRADLE=$JAVA_OPTS_GRADLE"
    echo "JAVA_OPTS_RUNTIME=$JAVA_OPTS_RUNTIME"
    echo "JAVA_OPTS_RUN_DEV=$JAVA_OPTS_RUN_DEV"
    echo "JAVA_OPTS_RUN_DEFAULT=$JAVA_OPTS_RUN_DEFAULT"
}

function showSettingsVava() {
    echo "JAVA Settings"
    WHICH_JAVA=`which java`
    WHICH_JAVAC=`which javac`
    WHICH_GRADLE=`which mvn`
    GRADLE_VERSION=`mvn -version`
    echo "which java=$WHICH_JAVA"
    echo "which javac=$WHICH_JAVAC"
    echo "which mvn=$WHICH_GRADLE"
    echo "mvn -version= $GRADLE_VERSION"
    echo "JAVA_HOME=$JAVA_HOME"
    echo "JAVA_OPTS=$JAVA_OPTS"
}

function showSettings() {
    echo "Show Environment"
    echo ""
    showSettings_bloodmoney_dbmaster
    echo ""
    showSettings_bloodmoney_dbref
    echo ""
    showSettingsPersistence
    echo ""
    showSettingsCredentialsForTesting
    echo ""
    showSettingsJavaOptsVariants
    echo ""
    showSettingsVava
    echo ""
    echo "SETUP Environment DONE"
    echo ""
}


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
    ./mvnw -e $TW_SKIP_TESTS clean install
    ./mvnw -e $TW_SKIP_TESTS dependency:tree dependency:resolve dependency:resolve-plugins dependency:sources
    ./mvnw -e $TW_SKIP_TESTS site site:deploy
    ./mvnw -e $TW_SKIP_TESTS clean install spring-boot:repackage
}

function setupTravis() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    export TW_SKIP_TESTS='-DskipTests=true'
    showSettings
    ./mvnw -e -DskipTests=true -B -V dependency:purge-local-repository
    ./mvnw -e -DskipTests=true -B -V clean
    ./mvnw -e -DskipTests=true -B -V dependency:resolve dependency:resolve-plugins dependency:sources
    ./mvnw -e -DskipTests=true -B -V dependency:tree
    ./mvnw -e -DskipTests=true -B -V docker-compose:up
     docker ps
    ./mvnw -e -DskipTests=true -B -V clean package
    ./mvnw -e -DskipTests=true -B -V site
    ./mvnw docker-compose:down
    docker ps
}

function setupTravis_tmp2() {
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    export TW_SKIP_TESTS='-DskipTests=true'
    showSettings
    ./mvnw -e $TW_SKIP_TESTS -B -V install -Dmaven.javadoc.skip=true && \
    ./mvnw -e $TW_SKIP_TESTS -B -V dependency:purge-local-repository clean && \
    ./mvnw -e $TW_SKIP_TESTS -B -V dependency:resolve dependency:resolve-plugins dependency:sources dependency:tree && \
    ./mvnw -e $TW_SKIP_TESTS -B -V clean package spring-boot:repackage && \
    ./mvnw -e $TW_SKIP_TESTS -B -V site site:deploy
}


function setupTravis_tmp() {
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

function releaseMe(){
    export JAVA_OPTS=$JAVA_OPTS_RUN_DEFAULT
    ./mvnw -e -DskipTests=true -B -V dependency:purge-local-repository
    ./mvnw -e -DskipTests=true -B -V clean install
    ./mvnw -e -DskipTests=true -B -V release:clean
    ./mvnw -e -DskipTests=true -B -V release:prepare && ./mvnw -e -DskipTests=true -B -V release:perform
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
