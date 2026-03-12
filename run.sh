#!/bin/bash
cd "$(dirname "$0")"
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-22.jdk/Contents/Home
mvn spring-boot:run