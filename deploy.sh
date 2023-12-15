#!/bin/sh

curl -Lo sbt https://raw.githubusercontent.com/sbt/sbt/v1.9.8/sbt

chmod +x ./sbt

./sbt buildWorkers