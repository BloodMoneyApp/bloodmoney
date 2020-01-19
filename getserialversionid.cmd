@echo OFF
echo STARTED
echo %CLASSPATH%
echo %JAVA_HOME%
echo %HOME%
set CLASSPATH=
@rem call mvnw.cmd --batch-mode --log-file=etc/mavenlog1.txt clean install -Pdeveloping -DskipTests=true -Dmaven.javadoc.skip=true -Dmdep.outputFile=etc\classpath.txt
echo START MAVEN 1
call mvnw.cmd --batch-mode --log-file=etc/mavenlog1.txt clean install -Pdeveloping -DskipTests=true -Dmaven.javadoc.skip=true
@rem call mvnw.cmd --batch-mode --log-file=etc/mavenlog3.txt dependency:unpack-dependencies
echo START MAVEN 2
call mvnw.cmd --batch-mode --log-file=etc/mavenlog2.txt dependency:build-classpath -Pdeveloping -Dmdep.outputFile=etc\classpath.txt
echo FINISHED MAVEN
set /P MY_CLASSPATH_DEPS= < etc\classpath.txt
set MY_CLASSPATH_APP=%JAVA_HOME%\jre\lib\rt.jar;%CD%\target\classes
set CLASSPATH=%MY_CLASSPATH_APP%;%MY_CLASSPATH_DEPS%
echo %CLASSPATH%
cd target\classes
echo START serialver
serialver org.woehlke.bloodmoney.oodm.model.parts.AuditModel > ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.oodm.model.BloodPressureMeasurement >> ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.frontend.model.FlashMessage >> ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.frontend.model.UserSession >> ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.config.ApplicationProperties >> ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.user.model.LoginForm >> ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.user.model.UserAccount >> ..\..\etc\serialversions.txt
serialver org.woehlke.bloodmoney.user.model.UserDetailsBean >> ..\..\etc\serialversions.txt
echo FINISHED
