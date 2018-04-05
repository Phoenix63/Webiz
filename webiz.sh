#!/bin/sh

echo "--Configuration of Webiz--\n"
if [ -e /usr/share/gradle/bin/gradle ]
then
    echo " Checking gradle package: OK"
else
    echo " Checking gradle package: Missing, please install the package to continue"
    exit -1
fi

if [ -e ~/Webiz.mv.db ]
then
    echo " Checking database: OK"
else
    echo " Checking database: No database found, creating..."
    java -cp lib/h2-1.4.196.jar org.h2.tools.RunScript -url jdbc:h2:~/Webiz -user sa -password "" -script startup.sql
fi

echo "\nConfiguration done, you can now use 'gradle run' to start Webiz"
exit 0

