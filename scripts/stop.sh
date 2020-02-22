#!/bin/sh

app_id=`ps -ef | grep "java -jar weather_api-0.0.1-SNAPSHOT.jar" | grep -v "grep" | awk '{print $2}'`
echo $app_id

for id in $app_id
do
    kill -9 $id  
    echo "killed $id"  
done
