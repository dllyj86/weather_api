#!/bin/bash
cd /home/ec2-user/weather_api

java -jar weather_api-0.0.1-SNAPSHOT.jar >api_log.log 2>&1 &
