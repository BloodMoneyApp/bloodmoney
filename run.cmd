call setenv.cmd

gradlew clean bootRun

java -jar build/libs/bloodmoney-1.0-SNAPSHOT.jar org.woehlke.bloodmoney.BloodMoneyApplication -Dspring.profiles.active=heroku

rem heroku login
rem heroku local web
