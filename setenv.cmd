echo "SETUP Environment"
echo ""
echo "PostgreSQL Database from Docker: bloodmoney_dbmaster"
set BLOODMONEY_DS_DB=bloodmoney
set BLOODMONEY_DS_USR=bloodmoney
set BLOODMONEY_DS_PWD=bloodmoneypwd
set BLOODMONEY_DS_HOST=localhost
set BLOODMONEY_DS_PORT=15432
echo ""
echo "PostgreSQL Database from Docker: bloodmoney_dbref"
set BLOODMONEY_DSREF_DB=bloodmoneyref
set BLOODMONEY_DSREF_USR=bloodmoneyref
set BLOODMONEY_DSREF_PWD=bloodmoneyrefpwd
set BLOODMONEY_DSREF_HOST=localhost
set BLOODMONEY_DSREF_PORT=25432
echo ""
echo "Persistence Settings"
set BLOODMONEY_HIBERNATE_DDL_AUTO=create-drop
echo ""
echo "Credentials for Testing"
set BLOODMONEY_USER_EMAIL=thomas.woehlke@gmail.com
set BLOODMONEY_USER_FULLNAME=Thomas Woehlke
set BLOODMONEY_USER_INTERATIONS=4096
set BLOODMONEY_USER_HASH_WIDTH=256
set BLOODMONEY_USER_SECRET=25D8484AB208F08C59FFAFE57DB9FF87
set BLOODMONEY_USER_PASSWORD=gdEB4vO/E8ArK1X1Cj2bnKGDPiJPUusEJfxxzpgUsf4G2fIrz+bHoQ==
echo ""
echo "Runtime and Application Service Settings"
set PORT=5000
set BLOODMONEY_DEV_TESTING=true
set SPRING_PROFILES_ACTIVE=dev
set JAVA_OPTS= -Dspring.profiles.active=dev -Djava.runtime.version=13 -Xmx2g -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8 -XX:CICompilerCount=2
echo ""
echo "SETUP Environment DONE"
echo ""
