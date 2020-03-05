call setenv.cmd

goto:MAIN

:bootRunHerokuLocal
echo -------------------------------- bootRunHerokuLocal --------------------------------------------
cmd /c gradlew -i composeUp
cmd /c gradlew -i clean assemble
cmd /c heroku local web
cmd /c gradlew -i composeDown
goto:END


:bootRunPostgresSQL
echo ---------------------------- bootRunPostgresSQL ------------------------------------------------
cmd /c gradlew -i clean bootJar
cmd /c gradlew -i composeUp
cmd /c gradlew -i bootRun --args='--spring.profiles.active=default'
cmd /c gradlew -i composeDown
goto:END

:testH2
echo ---------------------------- testH2 ------------------------------------------------
cmd /c gradlew -i clean build bootRun --args='--spring.profiles.active=dev'
goto:END


:bootRunH2
echo ---------------------------- bootRunH2 ------------------------------------------------
cmd /c gradlew -i clean bootRun --args='--spring.profiles.active=dev'
goto:END


:MAIN
rem goto:bootRunHerokuLocal
goto:bootRunPostgresSQL
rem goto:bootRunH2



:END
echo "DONE"
