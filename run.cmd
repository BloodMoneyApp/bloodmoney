call setenv.cmd

rem gradlew clean bootRun -Dspring.profiles.active=heroku

gradlew clean bootRun 

rem java -jar build/libs/bloodmoney-1.0-SNAPSHOT.jar org.woehlke.bloodmoney.BloodMoneyApplication -Dspring.profiles.active=heroku

rem heroku login
rem heroku local web
