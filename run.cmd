call setenv.cmd

cmd /c gradlew -i composeUp
cmd /c gradlew -i clean bootRun --args='--spring.profiles.active=heroku'
cmd /c gradlew -i composeDown

rem gradlew clean bootRun
rem gradlew clean bootJar
rem java -jar build/libs/bloodmoney-1.0-SNAPSHOT.jar org.woehlke.bloodmoney.BloodMoneyApplication -Dspring.profiles.active=default

