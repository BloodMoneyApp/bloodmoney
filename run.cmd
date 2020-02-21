call setenv.cmd

rem ----------------------------------------------------------------------------
rem cmd /c gradlew -i composeUp
rem cmd /c gradlew -i clean bootRun --args='--spring.profiles.active=heroku'
rem cmd /c gradlew -i composeDown

rem ----------------------------------------------------------------------------
cmd /c gradlew -i clean bootRun --args='--spring.profiles.active=default'
rem ----------------------------------------------------------------------------

rem gradlew clean bootRun
rem gradlew clean bootJar
rem java -jar build/libs/bloodmoney-1.0-SNAPSHOT.jar org.woehlke.bloodmoney.BloodMoneyApplication -Dspring.profiles.active=default

