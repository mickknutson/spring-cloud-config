#!/bin/bash

echo "Clean and verify the Maven build"

# Set settings file:

mvn clean verify -s ../../../Tools/custom-maven-settings.xml

echo "done..."
