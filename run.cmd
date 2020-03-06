call setenv.cmd

goto:MAIN

:bootRunHerokuLocal
echo -------------------------------- bootRun Heroku Local --------------------------------------------
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEFAULT%
set BLOODMONEY_DEV_TESTING=false
set JAVA_OPTS=%JAVA_OPTS_DEFAULT%
cmd /c gradlew -i clean assemble bootJar
cmd /c gradlew -i composeUp
cmd /c heroku local web
cmd /c gradlew -i composeDown
goto:END


:bootRunPostgresSQL
echo ---------------------------- bootRun PostgresSQL ------------------------------------------------
set BLOODMONEY_DEV_TESTING=false
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEFAULT%
set JAVA_OPTS=%JAVA_OPTS_DEFAULT%
cmd /c gradlew -i clean bootJar
cmd /c gradlew -i composeUp
cmd /c gradlew -i bootRun
cmd /c gradlew -i composeDown
goto:END

:testH2
echo ---------------------------- test H2 ------------------------------------------------
set BLOODMONEY_DEV_TESTING=false
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEV%
set JAVA_OPTS=%JAVA_OPTS_DEV%
cmd /c gradlew -i clean assemble bootJar build test check
goto:END


:bootRunH2
echo ---------------------------- bootRun H2 ------------------------------------------------
set JAVA_OPTS=%JAVA_OPTS_DEV%
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEV%
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
