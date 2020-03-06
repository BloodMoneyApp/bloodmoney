call setenv.cmd

goto:MAIN

:bootRunHerokuLocal
echo -------------------------------- bootRun Heroku Local --------------------------------------------
set JAVA_OPTS= -Dspring.profiles.active=default -Djava.runtime.version=13 -Xmx2g -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -XX:CICompilerCount=2
set BLOODMONEY_DEV_TESTING=false
cmd /c gradlew -i composeUp
cmd /c gradlew -i clean assemble
cmd /c heroku local web
cmd /c gradlew -i composeDown
goto:END


:bootRunPostgresSQL
echo ---------------------------- bootRun PostgresSQL ------------------------------------------------
set JAVA_OPTS= -Dspring.profiles.active=default -Djava.runtime.version=13 -Xmx2g -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -XX:CICompilerCount=2
set BLOODMONEY_DEV_TESTING=false
cmd /c gradlew -i clean bootJar
cmd /c gradlew -i composeUp
cmd /c gradlew -i bootRun
cmd /c gradlew -i composeDown
goto:END

:testH2
echo ---------------------------- test H2 ------------------------------------------------
set BLOODMONEY_DEV_TESTING=false
cmd /c gradlew -i clean build
rem cmd /c gradlew -i clean build
goto:END


:bootRunH2
echo ---------------------------- bootRun H2 ------------------------------------------------
set BLOODMONEY_DEV_TESTING=false
cmd /c gradlew -i clean bootRun
goto:END

:MAIN
rem goto:bootRunHerokuLocal
rem goto:bootRunPostgresSQL
rem goto:bootRunH2
goto:testH2

:END
echo "DONE"
