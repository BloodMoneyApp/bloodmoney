call setenv.cmd

mvnw -Pdefault clean spring-boot:run

rem mvnw -Pdefault clean package dependency:tree dependency:resolve dependency:resolve-plugins -DskipTests=true
