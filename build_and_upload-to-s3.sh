#!/bin/bash

sbt package
aws s3 cp ./target/scala-2.11/spark-kata_2.11-0.1-SNAPSHOT.jar s3://era-bigdata-demo/apps/spark-kata-${USER}.jar

