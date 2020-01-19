call setenv.cmd

gradlew clean bootJar
heroku login
heroku local web
