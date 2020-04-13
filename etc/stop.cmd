call setenv.cmd

set BLOODMONEY_DEV_TESTING=false
set SPRING_PROFILES_ACTIVE=%SPRING_PROFILES_ACTIVE_DEFAULT%
set JAVA_OPTS=%JAVA_OPTS_DEFAULT%
cmd /c ..\gradlew -i composeDown
cmd /c ..\gradlew -i clean
