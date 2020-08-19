call src\main\cmd\setenv.cmd

goto:MAIN

:runHerokuLocal
echo -------------------------------- bootRun Heroku Local --------------------------------------------
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEFAULT%
set BLOODMONEY_DEV_TESTING=false
set JAVA_OPTS=%JAVA_OPTS_DEFAULT%
cmd /c gradlew -i clean assemble bootJar
cmd /c gradlew -i composeUp
cmd /c heroku local web
cmd /c gradlew -i composeDown
goto:END


:run
echo ---------------------------- run PostgresSQL ------------------------------------------------
set BLOODMONEY_DEV_TESTING=false
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEFAULT%
set JAVA_OPTS=%JAVA_OPTS_DEFAULT%
cmd /c gradlew -i clean bootJar
cmd /c gradlew -i composeUp
cmd /c gradlew -i bootRun
cmd /c gradlew -i composeDown
goto:END

:test
echo ---------------------------- test PostgreSQL ------------------------------------------------
set BLOODMONEY_DEV_TESTING=false
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEFAULT%
set JAVA_OPTS=%JAVA_OPTS_DEFAULT%
cmd /c gradlew -i clean assemble bootJar
cmd /c gradlew -i composeUp
cmd /c gradlew -i build test check
cmd /c gradlew -i composeDown
goto:END

:MAIN
rem goto:runHerokuLocal
rem goto:test
goto:run

:END
echo "DONE"
