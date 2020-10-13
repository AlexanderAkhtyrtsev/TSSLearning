#!/bin/bash


rm -rf build
echo "building sources..."

javac -d build sources/*.java

echo "creating jar..."

cd build
jar --create --file lab3.jar --main-class=TitlesFrame *.class

