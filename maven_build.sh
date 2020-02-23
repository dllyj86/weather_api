#!/bin/bash
echo "======================================="
echo "Running mvn clean"
mvn clean

# package includes compile and test
echo "======================================="
echo "Running mvn package"
mvn package