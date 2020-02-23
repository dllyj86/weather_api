@echo off
cd ..
echo "======================================="
echo "Running mvn clean"
call mvn clean

echo "======================================="
echo "Running mvn package"
call mvn package

echo "======================================="
echo "Startup application in local"

call mvn exec:java

