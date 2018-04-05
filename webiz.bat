@echo off

echo --Configuration of Webiz--
echo You must be sure that gradle application is installed
echo You must be sure that the jdk and jre 1.8 is installed

If exist "C:%HOMEPATH%\Webiz.mv.db" (
	echo  Checking database: OK
) else (
	echo  Checking database: No database found, creating...
	java -cp lib/h2-1.4.196.jar org.h2.tools.RunScript -url jdbc:h2:~/Webiz -user sa -password "" -script startup.sql
)
echo Configuration done, you can now use 'gradle run' to start Webiz

pause