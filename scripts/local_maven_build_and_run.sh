#!/bin/bash
cd ..

echo "======================================="
echo "Running mvn clean"
mvn clean

# package includes compile and test
echo "======================================="
echo "Running mvn package"
mvn package

echo "======================================="
echo "Startup application in local"
mvn exec:java >./target/local_app_log.txt 2>&1 &
echo "You could see applicaton logs in target/local_app_log.txt"